package uz.medion.ui.main.user.ourDoctors

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.prolificinteractive.materialcalendarview.CalendarDay
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentOurDoctorsBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.gone
import uz.medion.utils.invisible
import uz.medion.utils.visible

class OurDoctorsFragment : BaseFragment<FragmentOurDoctorsBinding, OurDoctorsVM>() {

    private val args: OurDoctorsFragmentArgs by navArgs()
    private lateinit var ourDoctorsCategoryAdapter: OurDoctorsCategoryAdapter
    private lateinit var ourDoctorsDetailsAdapter: OurDoctorsDetailsAdapter
    private var tvCategoryAll: Boolean = false
    private var doctorsBySpecialityUrl: String = ""

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

    }

    private fun setUpUI() {
        binding.clShowAll.setOnClickListener {
            if (tvCategoryAll) {
                binding.rvDoctorsCategories.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                binding.clReason.gone()
                binding.clCalendar.gone()
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
//        val currentDate = CalendarDay.today()
//        val calendarState = binding.cvCalendar.state().edit()
//        calendarState.setMinimumDate(
//            CalendarDay.from(
//                currentDate.year,
//                currentDate.month,
//                currentDate.day
//            )
//        )
//        if (currentDate.month == 12)
//            calendarState.setMaximumDate(CalendarDay.from(currentDate.year + 1, 1, currentDate.day))
//        else calendarState.setMaximumDate(
//            CalendarDay.from(
//                currentDate.year,
//                currentDate.month + 1,
//                currentDate.day
//            )
//        )
//        calendarState.commit()

        // this should be changed totally
        // erase
        binding.clOption1.setOnClickListener {
            binding.ivOption1.visible()
            binding.ivOption2.gone()
            binding.ivOption3.gone()
        }
        binding.clOption2.setOnClickListener {
            binding.ivOption1.gone()
            binding.ivOption2.visible()
            binding.ivOption3.gone()
        }
        binding.clOption3.setOnClickListener {
            binding.ivOption1.gone()
            binding.ivOption2.gone()
            binding.ivOption3.visible()
        }
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

    override fun getLayoutResId() = R.layout.fragment_our_doctors
    override val vm: OurDoctorsVM
        get() = ViewModelProvider(this).get(OurDoctorsVM::class.java)

}