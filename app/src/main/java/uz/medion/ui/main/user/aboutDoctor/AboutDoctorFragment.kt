package uz.medion.ui.main.user.aboutDoctor

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applandeo.materialcalendarview.utils.DateUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.data.model.AboutDoctorItems
import uz.medion.data.model.AppointmentTimeItemIsClicked
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.remote.Status
import uz.medion.databinding.DialogAppointmentDateBinding
import uz.medion.databinding.DialogAppointmentSubspecialityBinding
import uz.medion.databinding.DialogAppointmentTimeBinding
import uz.medion.databinding.FragmentAboutDoctorBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.aboutDoctor.certificate.DoctorCertificateFragment
import uz.medion.ui.main.user.aboutDoctor.comments.DoctorCommentFragment
import uz.medion.ui.main.user.aboutDoctor.details.DoctorDetailsFragment
import uz.medion.ui.main.user.aboutDoctor.work.DoctorWorkFragment
import uz.medion.ui.main.user.ourDoctors.OurDoctorsSubSpecialityAdapter
import uz.medion.utils.ImageDownloader
import uz.medion.utils.invisible
import uz.medion.utils.visible
import java.util.*

class AboutDoctorFragment : BaseFragment<FragmentAboutDoctorBinding, AboutDoctorVM>() {

    private val args: AboutDoctorFragmentArgs by navArgs()
    private lateinit var aboutDoctorItemAdapter: AboutDoctorAdapter
    private lateinit var appointmentTimeAdapter: AppointmentTimeAdapter
    private lateinit var subSpecialityAdapter: OurDoctorsSubSpecialityAdapter
    private lateinit var dialogSubSpecialityBinding: DialogAppointmentSubspecialityBinding
    private lateinit var dialogDateBinding: DialogAppointmentDateBinding
    private lateinit var dialogTimeBinding: DialogAppointmentTimeBinding
    private lateinit var aboutDoctorItems: ArrayList<AboutDoctorItems>
    private lateinit var appointmentTimeIsClicked: ArrayList<AppointmentTimeItemIsClicked>
    private lateinit var subSpecialityIsClicked: ArrayList<AppointmentTimeItemIsClicked>
    private lateinit var doctorData: DoctorResponse
    private var dateDialog: BottomSheetDialog? = null
    private var timeDialog: BottomSheetDialog? = null
    private var subSpecialityDialog: BottomSheetDialog? = null
    private var appointmentDoctorName: String = ""
    private var subSpecialityId: Int = 1
    private var appointmentDate: Long = 0
    private var appointmentTime: String = ""
    private var appointmentType: String = ""
    val bundle = Bundle()


    override fun onBound() {
        loadAboutDoctorItems()
        setUp()
    }

    fun setUp() {
        if (args.doctorId != 0) {
            Log.d("----------", "setUp: doctorId: ${args.doctorId}")
            getAboutDoctor(args.doctorId)
        }

        appointmentType = args.appointmentType
        appointmentDoctorName = args.appointmentDoctorName


        binding.btnSubmit.setOnClickListener {
            loadSubSpecialityDialog()
            showSubSpecialityDialog()
        }
    }

    private fun loadSubSpecialityDialog() {
        dialogSubSpecialityBinding =
            DialogAppointmentSubspecialityBinding.inflate(LayoutInflater.from(requireContext()))
        subSpecialityAdapter = OurDoctorsSubSpecialityAdapter { subSpecialityId ->
            this.subSpecialityId = subSpecialityId + 1
        }
        dialogSubSpecialityBinding.btnSubmit.setOnClickListener {
            loadDateDialog()
            dismissSubSpecialityDialog()
            showCalendarDialog()
        }
        vm.getSubSpeciality(args.specialityId).observe(this) { subSpeciality ->
            when (subSpeciality.status) {
                Status.LOADING -> {
                    dialogSubSpecialityBinding.progress.visible()
                }
                Status.SUCCESS -> {
                    dialogSubSpecialityBinding.progress.invisible()
                    if (subSpeciality.data!!.isNotEmpty()) {
                        subSpecialityIsClicked = arrayListOf()
                        for (notCLicked in subSpeciality.data.indices) {
                            subSpecialityIsClicked.add(AppointmentTimeItemIsClicked(false))
                        }
                        subSpecialityIsClicked[0] = AppointmentTimeItemIsClicked(true)
                        subSpecialityAdapter.items = subSpeciality.data
                        subSpecialityAdapter.clickingItems = subSpecialityIsClicked
                    }
                    dialogSubSpecialityBinding.rvSubSpeciality.adapter = subSpecialityAdapter
                }
                Status.ERROR -> {}
            }
        }
    }

    //booking // dates
    @SuppressLint("SetTextI18n")
    private fun loadDateDialog() {
        dialogDateBinding =
            DialogAppointmentDateBinding.inflate(LayoutInflater.from(requireContext()))
        vm.getMonthlyDate(3).observe(this) { availableDates ->
            when (availableDates.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    val responseDateFirst = availableDates.data!![0].localDate!!.split("-")
                    val responseDateLast =
                        availableDates.data[availableDates.data.size - 1].localDate!!.split("-")
                    val min = Calendar.getInstance()
                    val max = Calendar.getInstance()
                    min.set(responseDateFirst[0].toInt(),
                        responseDateFirst[1].toInt() - 1,
                        responseDateFirst[2].toInt() - 1)
                    max.set(responseDateLast[0].toInt(),
                        responseDateLast[1].toInt(),
                        responseDateLast[2].toInt())

                    dialogDateBinding.calendarView.setMinimumDate(min)
                    dialogDateBinding.calendarView.setMaximumDate(max)
                    val calendars: MutableList<Calendar> = ArrayList()
                    for (day in availableDates.data.indices) {
                        if (availableDates.data[day].open == true) {
                            //2022-01-15
                            val responseDate = availableDates.data[day].localDate!!.split("-")
                            val firstDisabled: Calendar = DateUtils.getCalendar()
                            firstDisabled.set(responseDate[0].toInt(),
                                responseDate[1].toInt() - 1,
                                responseDate[2].toInt(),
                                0,
                                0,
                                0)
                            calendars.add(firstDisabled)
                        }
                    }
                    dialogDateBinding.calendarView.setDisabledDays(calendars)
                    //handle calendar day click
                    dialogDateBinding.calendarView.setOnDayClickListener {
                        val clickedDay: Calendar = DateUtils.getCalendar()
                        clickedDay.timeInMillis = it.calendar.timeInMillis
                        if (!calendars.contains(clickedDay)) {
                            appointmentDate = clickedDay.timeInMillis
                            dismissCalendarDialog()
                            loadTimeDialog()
                            showTimeDialog()
                        }
                    }
                }
                Status.ERROR -> {
                    Log.e("----------", "error: ${availableDates.message}")
                }
            }
        }
    }

    //booking // times
    private fun loadTimeDialog() {
        dialogTimeBinding =
            DialogAppointmentTimeBinding.inflate(LayoutInflater.from(requireContext()))
        appointmentTimeAdapter = AppointmentTimeAdapter { time ->
            appointmentTime = time.localTime.toString()
        }
        dialogTimeBinding.rvTime.adapter = appointmentTimeAdapter
        dialogTimeBinding.rvTime.layoutManager = GridLayoutManager(requireContext(), 4)

        vm.getMonthlyTime("2022-01-24", 3).observe(this) { time ->
            when (time.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    appointmentTimeIsClicked = arrayListOf()
                    for (notCLicked in time.data!!.indices) {
                        appointmentTimeIsClicked.add(AppointmentTimeItemIsClicked(false))
                    }
                    appointmentTimeAdapter.setData(time.data, appointmentTimeIsClicked)
                }
                Status.ERROR -> {
                }
            }
        }

        dialogTimeBinding.btnSubmit.setOnClickListener {
            dismissResultTimeDialog()
            dismissCalendarDialog()

            val action =
                AboutDoctorFragmentDirections.actionAboutDoctorFragmentToAppointmentEnrollFragment(
                    appointmentDoctorName,
                    args.doctorId,
                    appointmentDate,
                    appointmentTime,
                    appointmentType,
                    subSpecialityId)
            findNavController().navigate(action)
        }
    }

    //fragments // details / work / comment / certificate
    private fun loadAboutDoctorItems() {
        aboutDoctorItems = Constants.getAboutDoctorItems()
        aboutDoctorItemAdapter = AboutDoctorAdapter { position, lastPosition ->
            if (position != lastPosition) {
                aboutDoctorItems[position] =
                    AboutDoctorItems(
                        aboutDoctorItems[position].categoryName,
                        R.color.nile_blue_900,
                        R.color.white
                    )
                aboutDoctorItems[lastPosition] = AboutDoctorItems(
                    aboutDoctorItems[lastPosition].categoryName,
                    R.color.solitude_50,
                    R.color.tangaroa_900
                )
                aboutDoctorItemAdapter.setData(aboutDoctorItems)

                when (position) {
                    0 -> beginDoctorDetailsFragment()
                    1 -> beginDoctorWorkFragment()
                    2 -> beginDoctorCommentFragment()
                    3 -> beginDoctorCertificateFragment()
                }
            }
        }
        aboutDoctorItemAdapter.setData(aboutDoctorItems)
        binding.rvDoctorAboutDetails.adapter = aboutDoctorItemAdapter
        binding.rvDoctorAboutDetails.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    private fun getAboutDoctor(doctorId: Int) {
        vm.getDoctorById(doctorId).observe(this) { doctorData ->
            when (doctorData.status) {
                Status.LOADING -> {
                    binding.clTop.invisible()
                }
                Status.SUCCESS -> {
                    binding.clTop.visible()
                    this.doctorData = doctorData.data!!
                    beginDoctorDetailsFragment()
                    binding.tvFullName.text = doctorData.data.fullName
                    binding.tvReyting.text = doctorData.data.averageRating
                    binding.tvExperience.text = doctorData.data.workExperience
                    binding.tvCategory.text = doctorData.data.workInfoList!![0]!!.position
                    ImageDownloader.loadImage(requireContext(),
                        doctorData.data.image!!,
                        binding.cardProfileAvater)

                    appointmentDoctorName = doctorData.data.fullName.toString()
                }
                Status.ERROR -> {
                    binding.clTop.visible()
                }
            }
        }
    }

    private fun beginDoctorDetailsFragment() {
        val doctorDetailsFragment: Fragment = DoctorDetailsFragment()

        //given arguments: about, url, university and faculty
        bundle.putString(Keys.BUNDLE_ABOUT_DOCTOR_UNIVERSITY,
            doctorData.educationInfoList!![0]!!.organization)
        bundle.putString(Keys.BUNDLE_ABOUT_DOCTOR_FACULTY,
            doctorData.educationInfoList!![0]!!.faculty)
        bundle.putString(Keys.BUNDLE_ABOUT_DOCTOR_URL, doctorData.doctorVideoUrl)
        bundle.putString(Keys.BUNDLE_ABOUT_DOCTOR, doctorData.aboutDoctor)
        doctorDetailsFragment.arguments = bundle

        val transaction: FragmentTransaction =
            childFragmentManager.beginTransaction()
        transaction.replace(binding.parentFragmentContainer.id,
            doctorDetailsFragment).commit()
    }

    private fun beginDoctorWorkFragment() {
        val doctorWorkFragment: Fragment = DoctorWorkFragment()
        val transaction: FragmentTransaction =
            childFragmentManager.beginTransaction()

        //give argument: list of work
        bundle.putParcelableArrayList(Keys.BUNDLE_ABOUT_DOCTOR_WORK,
            doctorData.workInfoList as ArrayList)
        doctorWorkFragment.arguments = bundle

        transaction.replace(binding.parentFragmentContainer.id, doctorWorkFragment)
            .commit()
    }

    private fun beginDoctorCommentFragment() {
        val doctorCommentFragment: Fragment = DoctorCommentFragment()
        val transaction: FragmentTransaction =
            childFragmentManager.beginTransaction()

        //give argument: doctor ID
        bundle.putInt(Keys.BUNDLE_ABOUT_DOCTOR_ID, args.doctorId)
        doctorCommentFragment.arguments = bundle

        transaction.replace(binding.parentFragmentContainer.id,
            doctorCommentFragment).commit()
    }

    private fun beginDoctorCertificateFragment(){
        val doctorCertificateFragment: Fragment = DoctorCertificateFragment()
        val transaction: FragmentTransaction =
            childFragmentManager.beginTransaction()

        transaction.replace(binding.parentFragmentContainer.id,
            doctorCertificateFragment)
            .commit()
    }

    private fun showCalendarDialog() {
        dateDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        dateDialog!!.setContentView(dialogDateBinding.root)
        dateDialog!!.show()
    }

    private fun showTimeDialog() {
        timeDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        timeDialog!!.setContentView(dialogTimeBinding.root)
        timeDialog!!.show()
    }

    private fun showSubSpecialityDialog() {
        subSpecialityDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        subSpecialityDialog!!.setContentView(dialogSubSpecialityBinding.root)
        subSpecialityDialog!!.show()
    }

    private fun dismissCalendarDialog() {
        if (dateDialog!!.isShowing) {
            dateDialog!!.dismiss()
        }
        dateDialog!!.dismiss()
    }

    private fun dismissResultTimeDialog() {
        if (timeDialog!!.isShowing) {
            timeDialog!!.dismiss()
        }
        timeDialog!!.dismiss()
    }

    private fun dismissSubSpecialityDialog() {
        if (subSpecialityDialog!!.isShowing) {
            subSpecialityDialog!!.dismiss()
        }
        subSpecialityDialog!!.dismiss()
    }

    override fun getLayoutResId() = R.layout.fragment_about_doctor
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this)[AboutDoctorVM::class.java]

}