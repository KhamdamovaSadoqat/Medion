package uz.medion.ui.main.user.ourDoctors

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.AppointmentTimeItemIsClicked
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentOurDoctorsBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.DateTimeUtils
import uz.medion.utils.gone
import uz.medion.utils.invisible
import uz.medion.utils.visible
import java.util.*

class OurDoctorsFragment : BaseFragment<FragmentOurDoctorsBinding, OurDoctorsVM>() {

    private val args: OurDoctorsFragmentArgs by navArgs()
    private lateinit var ourDoctorsCategoryAdapter: OurDoctorsCategoryAdapter
    private lateinit var ourDoctorsDetailsAdapter: OurDoctorsDetailsAdapter
    private lateinit var subSpecialityAdapter: OurDoctorsSubSpecialityAdapter
    private lateinit var subSpecialityIsClicked: ArrayList<AppointmentTimeItemIsClicked>
    private var tvCategoryAll: Boolean = false
    private var doctorsBySpecialityUrl: String = ""
    private var chosenDate: String = ""
    private var subSpecialityId: Int = 1

    //SpecialityId can be get by Arguments:  args.specialityTypeId
    //SubSpecialityId default = 0
    //dateFormat:  "yyyy-mm-dd"

    override fun onBound() {
        setUpUI()
        setUp()
    }

    fun setUp() {
        if (args.specialityTypeId == 0) {
            //get all doctors that click has
        } else
            doctorsBySpecialityUrl =
                "${Constants.BASE_API_URL}/api/v1/speciality/${args.specialityTypeId}/doctors"
        getDoctors(doctorsBySpecialityUrl)
        getSpecialities()
        getSubSpeciality(args.specialityTypeId)
    }

    private fun setUpUI() {
        subSpecialityAdapter = OurDoctorsSubSpecialityAdapter { subSpecialityId ->
            this.subSpecialityId = subSpecialityId+1
        }
        binding.rvSubSpeciality.adapter = subSpecialityAdapter

        binding.clShowAll.setOnClickListener {
            if (tvCategoryAll) {
                binding.rvDoctorsCategories.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                binding.clReason.gone()
                binding.clCalendar.gone()
                binding.rvSubSpeciality.gone()
                binding.ivShowAll.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_show_all
                    )
                )
                binding.tvShowAll.setText(R.string.show_all)
                tvCategoryAll = false
            } else {
                val flexboxLayoutManager = FlexboxLayoutManager(context)
                flexboxLayoutManager.flexDirection = FlexDirection.ROW
                flexboxLayoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
                binding.rvDoctorsCategories.removeAllViews()
                binding.rvDoctorsCategories.layoutManager = flexboxLayoutManager
                binding.clReason.visible()
                binding.clCalendar.visible()
                binding.rvSubSpeciality.visible()
                binding.ivShowAll.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_hide_all
                    )
                )
                binding.tvShowAll.setText(R.string.hide_all)
                tvCategoryAll = true
            }
        }

        //calendar modifying
        binding.calendarView.currentPageDate
        binding.calendarView.setOnDayClickListener{ eventDay ->
            if (eventDay.isEnabled && eventDay.calendar.get(Calendar.MONTH) == binding.calendarView.currentPageDate.get(
                    Calendar.MONTH)
            ) {
                //Fri Feb 11 00:00:00 GMT+05:00 2022
                chosenDate = DateTimeUtils.timeMillsToTextDate2(eventDay.calendar.timeInMillis)
                getFilterDoctors(chosenDate, args.specialityTypeId, subSpecialityId)
            }
        }
        val currentDate = Calendar.getInstance()
        val min = Calendar.getInstance()
        val max = Calendar.getInstance()
        min.timeInMillis = currentDate.timeInMillis - 86400000
        max.timeInMillis = currentDate.timeInMillis + 2592000000 // +1 month
        binding.calendarView.setMinimumDate(min)
        binding.calendarView.setMaximumDate(max)

    }

    private fun getSpecialities() {
        vm.speciality().observe(this) { speciality ->
            when (speciality.status) {
                Status.LOADING -> {
                    binding.progressForRv.visible()
                }
                Status.SUCCESS -> {
                    binding.progressForRv.invisible()
                    binding.rvDoctorsCategories.visible()

                    ourDoctorsCategoryAdapter = OurDoctorsCategoryAdapter { specialityId ->
                        doctorsBySpecialityUrl =
                            "${Constants.BASE_API_URL}/api/v1/speciality/$specialityId/doctors"
                        getDoctors(doctorsBySpecialityUrl)
                    }
                    ourDoctorsCategoryAdapter.setData(speciality.data!!)
                    binding.rvDoctorsCategories.adapter = ourDoctorsCategoryAdapter
                    binding.rvDoctorsCategories.layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                }
                Status.ERROR -> {
                    binding.progressForRv.invisible()
                }
            }
        }
    }

    private fun getDoctors(url: String) {
        vm.doctorBySpeciality(url).observe(this) { doctors ->
            when (doctors.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvDoctors.visible()
                    ourDoctorsDetailsAdapter = OurDoctorsDetailsAdapter { doctorId ->
                        val action =
                            OurDoctorsFragmentDirections.actionOurDoctorsFragmentToAboutDoctorFragment(
                                doctorId)
                        findNavController().navigate(action)
                    }
                    ourDoctorsDetailsAdapter.setData(doctors.data!!)
                    binding.rvDoctors.adapter = ourDoctorsDetailsAdapter
                    binding.rvDoctors.layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                }
                Status.ERROR -> {
                    binding.progress.invisible()
                }
            }
        }
    }

    private fun getSubSpeciality(specialityId: Int) {
        vm.subSpeciality(specialityId).observe(this) { subSpeciality ->
            when (subSpeciality.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    subSpecialityIsClicked = arrayListOf()
                    for (notCLicked in subSpeciality.data!!.indices) {
                        subSpecialityIsClicked.add(AppointmentTimeItemIsClicked(false))
                    }
                    subSpecialityIsClicked[0] = AppointmentTimeItemIsClicked(true)
                    subSpecialityAdapter.items = subSpeciality.data
                    subSpecialityAdapter.clickingItems = subSpecialityIsClicked
                }
                Status.ERROR -> {
                }
            }

        }
    }

    //{"code":1002,"message":"Doctor doesn't work in selected date","meta":null}
    private fun getFilterDoctors(date: String, specialityId: Int, subSpecialityId: Int) {
        vm.filterDoctors(date, specialityId, subSpecialityId).observe(this) { doctorList ->
            when (doctorList.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    ourDoctorsDetailsAdapter.setData(doctorList.data!!)
                }
                Status.ERROR -> {
                    if(doctorList.message == "Doctor doesn't work in selected date"){
                        Log.d("----------", "getFilterDoctors: removing")
                        val list = listOf<DoctorResponse>()
                        ourDoctorsDetailsAdapter.setData(list)
                    }
                }
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_our_doctors
    override val vm: OurDoctorsVM
        get() = ViewModelProvider(this)[OurDoctorsVM::class.java]

}