package uz.medion.ui.main.user.aboutDoctor

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applandeo.materialcalendarview.utils.DateUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.data.model.AboutDoctorItems
import uz.medion.data.model.AppointmentTimeItem
import uz.medion.databinding.DialogAppointmentBinding
import uz.medion.databinding.DialogAppointmentTimeBinding
import uz.medion.databinding.FragmentAboutDoctorBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.appointment.AppointmentTimeAdapter
import java.util.*
import uz.medion.data.model.remote.Status

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.navArgs
import uz.medion.data.model.DoctorResponse
import uz.medion.utils.ImageDownloader
import uz.medion.utils.invisible
import uz.medion.utils.visible
import android.os.Bundle
import android.os.Parcelable
import uz.medion.data.model.WorkInfoListItem


class AboutDoctorFragment : BaseFragment<FragmentAboutDoctorBinding, AboutDoctorVM>() {

    private val args: AboutDoctorFragmentArgs by navArgs()

    private lateinit var aboutDoctorItemAdapter: AboutDoctorAdapter
    private lateinit var appointmentTimeAdapter: AppointmentTimeAdapter
    private lateinit var dialogBinding: DialogAppointmentBinding
    private lateinit var dialogTimeBinding: DialogAppointmentTimeBinding
    private lateinit var aboutDoctorItems: ArrayList<AboutDoctorItems>
    private lateinit var appointmentTime: ArrayList<AppointmentTimeItem>
    private lateinit var doctorData: DoctorResponse
    private var resultDialog: BottomSheetDialog? = null
    private var resultTimeDialog: BottomSheetDialog? = null
    private var appointmentTimeBundle: String = "08:30"
    private var appointmentDateBundle: String = ""
    private var doctorName: String = ""
    private var type: String = ""


    override fun onBound() {
        loadAboutDoctorItems()
        setUp()
    }

    fun setUp() {
        if (args.doctorId != 0) {
            Log.d("----------", "setUp: doctorId: ${args.doctorId}")
            getAboutDoctor(args.doctorId)
        }

        /// from here
        val argsBundle = arguments
        if (argsBundle != null) {
            if (argsBundle.containsKey(Keys.BUNDLE_APPOINTMENT_TYPE)) {
                type = argsBundle.get(Keys.BUNDLE_APPOINTMENT_TYPE) as String
                doctorName = requireArguments().get(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME) as String
                loadDialog()

            } else if (argsBundle.containsKey(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME)) {
                doctorName = requireArguments().get(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME) as String
            }
        }   //// up to here need to change to safeArgs

        binding.btnSubmit.setOnClickListener {
            findNavController().navigate(
                R.id.appointmentFragment,
                bundleOf(
                    Pair(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME, doctorName)
                )
            )
        }

    }

    //booking // dates
    @SuppressLint("SetTextI18n")
    private fun loadDialog() {
        dialogBinding = DialogAppointmentBinding.inflate(LayoutInflater.from(requireContext()))
        vm.monthlyDate(3).observe(this) { availableDates ->
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

                    dialogBinding.calendarView.setMinimumDate(min)
                    dialogBinding.calendarView.setMaximumDate(max)
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
                    dialogBinding.calendarView.setDisabledDays(calendars)
                    //handle calendar day click
                    dialogBinding.calendarView.setOnDayClickListener {
                        val clickedDay: Calendar = DateUtils.getCalendar()
                        clickedDay.timeInMillis = it.calendar.timeInMillis
                        if (!calendars.contains(clickedDay)) {
                            dismissCalendarDialog()
                            loadResultTimeDialog()
                            showTimeDialog()
                        }
                    }
                    showCalendarDialog()
                }
                Status.ERROR -> {
                    Log.e("----------", "error: ${availableDates.message}")
                }
            }
        }
    }

    //booking // times
    private fun loadResultTimeDialog() {
        dialogTimeBinding =
            DialogAppointmentTimeBinding.inflate(LayoutInflater.from(requireContext()))
        vm.monthlyTime("2022-01-24", 3).observe(this) { time ->
            when (time.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    appointmentTime = Constants.getAppointmentTime()
                    Log.d("----------", "loadResultTimeDialog: times: ${time.data!!.times}")
                    /////////////////////////////////////
                    /////////////////////////////////////
                    /////////////////////////////////////
                    /////////////////////////////////////
                    /////////////////////////////////////

                    // here left undone


                }
                Status.ERROR -> {
                }
            }
        }
        appointmentTime = Constants.getAppointmentTime()
        appointmentTimeAdapter = AppointmentTimeAdapter { position, lastPosition ->
            appointmentTimeBundle = requireContext().getString(appointmentTime[position].time)

            if (position != lastPosition) {
                appointmentTime[position] =
                    AppointmentTimeItem(
                        R.drawable.bg_blue_lighter_8,
                        appointmentTime[position].time,
                        R.color.white,
                        clickable = false,
                        focusable = false
                    )
                appointmentTime[lastPosition] =
                    AppointmentTimeItem(
                        R.drawable.bg_transparent_4,
                        appointmentTime[lastPosition].time,
                        R.color.tangaroa_900,
                        clickable = false,
                        focusable = false
                    )
                appointmentTimeAdapter.setData(appointmentTime)
            }
        }
        appointmentTimeAdapter.setData(appointmentTime)
        dialogTimeBinding.rvTime.adapter = appointmentTimeAdapter
        dialogTimeBinding.rvTime.layoutManager = GridLayoutManager(requireContext(), 4)

//        appointmentDateBundle = "${date.day}.${date.month}.${date.year}"
        dialogTimeBinding.btnSubmit.setOnClickListener {
            dismissResultTimeDialog()
            dismissCalendarDialog()
            findNavController().navigate(
                R.id.appointmentEnrollFragment,
//                bundleOf(
//                    Pair(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME, doctorName),
//                    Pair(Keys.BUNDLE_APPOINTMENT_TIME, appointmentTimeBundle),
//                    Pair(Keys.BUNDLE_APPOINTMENT_DATE, appointmentDateBundle),
//                    Pair(Keys.BUNDLE_APPOINTMENT_TYPE, type)
//                )
            )
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
                    0 -> {
                      beginDoctorDetailsFragment()
                    }
                    1 -> {
                        val doctorWorkFragment: Fragment = DoctorWorkFragment()
                        val transaction: FragmentTransaction =
                            childFragmentManager.beginTransaction()
                        transaction.replace(binding.parentFragmentContainer.id, doctorWorkFragment)
                            .commit()
                    }
                    2 -> {
                        val doctorCommentFragment: Fragment = DoctorCommentFragment()
                        val transaction: FragmentTransaction =
                            childFragmentManager.beginTransaction()
                        transaction.replace(binding.parentFragmentContainer.id,
                            doctorCommentFragment).commit()
                    }
                    3 -> {
                        val doctorCertificateFragment: Fragment = DoctorCertificateFragment()
                        val transaction: FragmentTransaction =
                            childFragmentManager.beginTransaction()
                        transaction.replace(binding.parentFragmentContainer.id,
                            doctorCertificateFragment)
                            .commit()
                    }
                }
            }
        }
        aboutDoctorItemAdapter.setData(aboutDoctorItems)
        binding.rvDoctorAboutDetails.adapter = aboutDoctorItemAdapter
        binding.rvDoctorAboutDetails.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

    }

    private fun getAboutDoctor(doctorId: Int) {
        vm.doctorById(doctorId).observe(this) { doctorData ->
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
                }
                Status.ERROR -> {
                    binding.clTop.visible()
                }
            }

        }
    }

    private fun beginDoctorDetailsFragment(){
        val doctorDetailsFragment: Fragment = DoctorDetailsFragment()

        //given arguments: about, url, university and faculty
        val bundle = Bundle()

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

    private fun showCalendarDialog() {
        resultDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        resultDialog!!.setContentView(dialogBinding.root)
        resultDialog!!.show()
    }

    private fun showTimeDialog() {
        resultTimeDialog = BottomSheetDialog(requireContext(), R.style.SheetDialog)
        resultTimeDialog!!.setContentView(dialogTimeBinding.root)
        resultTimeDialog!!.show()
    }

    private fun dismissCalendarDialog() {
        if (resultDialog!!.isShowing) {
            resultDialog!!.dismiss()
        }
        resultDialog!!.dismiss()
    }

    private fun dismissResultTimeDialog() {
        if (resultTimeDialog!!.isShowing) {
            resultTimeDialog!!.dismiss()
        }
        resultTimeDialog!!.dismiss()
    }

    override fun getLayoutResId() = R.layout.fragment_about_doctor
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)

}