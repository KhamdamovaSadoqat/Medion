package uz.medion.ui.main.user.aboutDoctor

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.prolificinteractive.materialcalendarview.CalendarDay
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.data.model.AboutDoctorCommentItem
import uz.medion.data.model.AboutDoctorItems
import uz.medion.data.model.AppointmentTimeItem
import uz.medion.databinding.DialogAppointmentBinding
import uz.medion.databinding.DialogAppointmentTimeBinding
import uz.medion.databinding.FragmentAboutDoctorBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.appointment.AppointmentTimeAdapter
import uz.medion.utils.DateTimeUtils
import java.util.*


class AboutDoctorFragment : BaseFragment<FragmentAboutDoctorBinding, AboutDoctorVM>(){

    private lateinit var aboutDoctorCertificateAdapter: AboutDoctorCertificateAdapter
    private lateinit var aboutDoctorWorkCurrentAdapter: AboutDoctorWorkAdapter
    private lateinit var aboutDoctorCommentAdapter: AboutDoctorCommentAdapter
    private lateinit var aboutDoctorWorkPastAdapter: AboutDoctorWorkAdapter
    private lateinit var aboutDoctorItemAdapter: AboutDoctorAdapter
    private lateinit var appointmentTimeAdapter: AppointmentTimeAdapter
    private lateinit var dialogBinding: DialogAppointmentBinding
    private lateinit var dialogTimeBinding: DialogAppointmentTimeBinding
    private lateinit var data: ArrayList<AboutDoctorItems>
    private lateinit var appointmentTime: ArrayList<AppointmentTimeItem>
    private var resultDialog: BottomSheetDialog? = null
    private var resultTimeDialog: BottomSheetDialog? = null
    private var reyting: Int = 1
    private var date = Date()
    private var time: String = "hh:mm dd.mm.yyyy"
    private var appointmentTimeBundle: String = "08:30"
    private var appointmentDateBundle: String = ""
    private var doctorName: String = ""
    private var type: String = ""


    override fun onBound() {
        loadAboutDoctor()
        loadDialog()
        setUp()
    }

    fun setUp() {
        val args = arguments
        if (args != null) {
            if (args.containsKey(Keys.BUNDLE_APPOINTMENT_TYPE)) {
                type = args.get(Keys.BUNDLE_APPOINTMENT_TYPE) as String
                doctorName = requireArguments().get(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME) as String
                showCalendarDialog()
            } else if (args.containsKey(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME)) {
                doctorName = requireArguments().get(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME) as String
            }
        }
        binding.btnSubmit.setOnClickListener {
            findNavController().navigate(
                R.id.appointmentFragment,
                bundleOf(
                    Pair(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME, doctorName)
                )
            )
        }
        lifecycle.addObserver(binding.youtubePlayerView)
    }

    @SuppressLint("SetTextI18n")
    private fun loadDialog() {
        dialogBinding = DialogAppointmentBinding.inflate(LayoutInflater.from(requireContext()))
        val currentDate = CalendarDay.today()
        val calendarState = dialogBinding.cvCalendar.state().edit()
        calendarState.setMinimumDate(
            CalendarDay.from(
                currentDate.year,
                currentDate.month,
                currentDate.day
            )
        )
        if (currentDate.month == 12)
            calendarState.setMaximumDate(
                CalendarDay.from(
                    currentDate.year + 1,
                    0,
                    currentDate.day
                )
            )
        calendarState.setMaximumDate(
            CalendarDay.from(
                currentDate.year,
                currentDate.month + 1,
                currentDate.day
            )
        )
        calendarState.commit()
        dialogBinding.cvCalendar.setOnDateChangedListener { widget, date, selected ->
            loadResultTimeDialog(date)
            dismissCalendarDialog()
        }
    }

    private fun loadResultTimeDialog(date: CalendarDay) {
        dialogTimeBinding =
            DialogAppointmentTimeBinding.inflate(LayoutInflater.from(requireContext()))

        appointmentTime = Constants.getAppointmentTime()
        appointmentTimeAdapter = AppointmentTimeAdapter { position, lastPosition ->
            appointmentTimeBundle = requireContext().getString(appointmentTime[position].time)

            if (position != lastPosition) {
                appointmentTime[position] =
                    AppointmentTimeItem(
                        R.drawable.bg_blue_lighter_8, appointmentTime[position].time, R.color.white
                    )
                appointmentTime[lastPosition] =
                    AppointmentTimeItem(
                        R.drawable.bg_transparent_4,
                        appointmentTime[lastPosition].time,
                        R.color.tangaroa_900
                    )
                appointmentTimeAdapter.setData(appointmentTime)
            }
        }
        appointmentTimeAdapter.setData(appointmentTime)
        dialogTimeBinding.rvTime.adapter = appointmentTimeAdapter
        dialogTimeBinding.rvTime.layoutManager = GridLayoutManager(requireContext(), 4)

        appointmentDateBundle = "${date.day}.${date.month}.${date.year}"
        dialogTimeBinding.btnSubmit.setOnClickListener {
            dismissResultTimeDialog()
            dismissCalendarDialog()
            findNavController().navigate(
                R.id.appointmentEnrollFragment,
                bundleOf(
                    Pair(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME, doctorName),
                    Pair(Keys.BUNDLE_APPOINTMENT_TIME, appointmentTimeBundle),
                    Pair(Keys.BUNDLE_APPOINTMENT_DATE, appointmentDateBundle),
                    Pair(Keys.BUNDLE_APPOINTMENT_TYPE, type)
                )
            )
        }
        showTimeDialog()
    }

    private fun loadAboutDoctor() {
        // setting history of doctor
        data = Constants.getAboutDoctorItems()
        aboutDoctorItemAdapter = AboutDoctorAdapter { position, lastPosition ->
            if (position != lastPosition) {
                data[position] =
                    AboutDoctorItems(
                        data[position].categoryName,
                        R.color.nile_blue_900,
                        R.color.white
                    )
                data[lastPosition] = AboutDoctorItems(
                    data[lastPosition].categoryName,
                    R.color.solitude_50,
                    R.color.tangaroa_900
                )
                aboutDoctorItemAdapter.setData(data)

                aboutDoctorCommentAdapter = AboutDoctorCommentAdapter { }
                val dataa = Constants.getComments()
                Log.d("-------------", "setUp: size: ${dataa.size}")
                aboutDoctorCommentAdapter.setData(dataa)
                binding.rvComments.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

                when (position) {
                    0 -> {
                        binding.clAboutDoctor.visibility = View.VISIBLE
                        binding.clComment.visibility = View.GONE
                        binding.clWork.visibility = View.GONE
                        binding.clSertificate.visibility = View.GONE
                    }
                    1 -> {
                        loadWork()
                        binding.clAboutDoctor.visibility = View.GONE
                        binding.clWork.visibility = View.VISIBLE
                        binding.clComment.visibility = View.GONE
                        binding.clSertificate.visibility = View.GONE
                    }
                    2 -> {
                        loadComment()
                        binding.clAboutDoctor.visibility = View.GONE
                        binding.clWork.visibility = View.GONE
                        binding.clComment.visibility = View.VISIBLE
                        binding.clSertificate.visibility = View.GONE
                    }
                    3 -> {
                        loadCertificate()
                        binding.clAboutDoctor.visibility = View.GONE
                        binding.clWork.visibility = View.GONE
                        binding.clComment.visibility = View.GONE
                        binding.clSertificate.visibility = View.VISIBLE
                    }
                }
            }
        }
        aboutDoctorItemAdapter.setData(data)
        binding.rvDoctorAboutDetails.adapter = aboutDoctorItemAdapter
        binding.rvDoctorAboutDetails.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

    }

    private fun loadWork() {
        //workCurrent
        aboutDoctorWorkCurrentAdapter = AboutDoctorWorkAdapter()
        aboutDoctorWorkCurrentAdapter.setData(Constants.getCurrentWork())
        binding.rvWorkCurrent.adapter = aboutDoctorWorkCurrentAdapter
        binding.rvWorkCurrent.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        //workPast
        aboutDoctorWorkPastAdapter = AboutDoctorWorkAdapter()
        aboutDoctorWorkPastAdapter.setData(Constants.getPastWork())
        binding.rvWorkPast.adapter = aboutDoctorWorkCurrentAdapter
        binding.rvWorkPast.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }

    private fun loadComment() {
        //generating comments from constant date
        aboutDoctorCommentAdapter = AboutDoctorCommentAdapter { }
        aboutDoctorCommentAdapter.setData(Constants.getComments())
        binding.rvComments.adapter = aboutDoctorCommentAdapter
        binding.rvComments.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        // rating the doctor
        binding.ivStar1.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
            }
            reyting = 1
        }
        binding.ivStar2.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
            }
            reyting = 2
        }
        binding.ivStar3.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
            }
            reyting = 3
        }
        binding.ivStar4.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
            }
            reyting = 4
        }
        binding.ivStar5.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
            }
            reyting = 5
        }

        //sending comment
        binding.tvCommentSend.setOnClickListener {
            date.time = System.currentTimeMillis()
            time = DateTimeUtils.dateToText(date, "HH:mm yyyy-MM-dd").toString()
            if (binding.etUserComment.text.isNullOrEmpty() || binding.etUserComment.text.isNullOrBlank()) {
                binding.etUserComment.error = "Write comment please!"
            } else {
                vm.sendComment(
                    AboutDoctorCommentItem(
                        binding.etUserComment.text.toString(),
                        reyting,
                        time
                    )
                )
                reloadLayout()
            }
        }

        //undo comment
        binding.tvCommentUndo.setOnClickListener {
            reloadLayout()
        }

    }

    private fun loadCertificate() {
        //certificate
        aboutDoctorCertificateAdapter = AboutDoctorCertificateAdapter {
            findNavController().navigate(
                R.id.action_aboutDoctorFragment_to_certificateFragment, bundleOf(
                    Keys.BUNDLE_CERTIFICATE to it
                )
            )
        }
        aboutDoctorCertificateAdapter.setData(Constants.getSertificate())
        binding.rvSertificate.adapter = aboutDoctorCertificateAdapter
        binding.rvSertificate.layoutManager = GridLayoutManager(requireContext(), 2)

    }

    private fun reloadLayout() {
        binding.etUserComment.setText("")
        binding.apply {
            ivStar1.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
            ivStar2.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
            ivStar3.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
            ivStar4.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
            ivStar5.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
        }
        reyting = 1
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