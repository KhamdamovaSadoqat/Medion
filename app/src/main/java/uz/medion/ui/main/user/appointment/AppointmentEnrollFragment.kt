package uz.medion.ui.main.user.appointment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentAppointmentEnrollBinding
import uz.medion.ui.base.BaseFragment

class AppointmentEnrollFragment : BaseFragment<FragmentAppointmentEnrollBinding, AppointmentVM>() {

    private var doctorName: String = ""
    private var date: String = ""
    private var time: String = ""
    private var type: String = ""

    override fun onBound() {
        setUp()
    }

    @SuppressLint("SetTextI18n")
    fun setUp() {
        doctorName = requireArguments().get(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME) as String
        date = requireArguments().get(Keys.BUNDLE_APPOINTMENT_DATE) as String
        time = requireArguments().get(Keys.BUNDLE_APPOINTMENT_TIME) as String
        type = requireArguments().get(Keys.BUNDLE_APPOINTMENT_TYPE) as String


        binding.tvFullName.text = doctorName
        binding.tvDataTime.text = "$date, $time"
        binding.tvConsultationType.text = type

        binding.chbCard.setOnClickListener {
            binding.chbCash.isChecked = false
        }
        binding.chbCash.setOnClickListener {
            binding.chbCard.isChecked = false
        }

        binding.clAdd.setOnClickListener {
            findNavController().navigate(R.id.action_appointmentEnrollFragment_to_addCardFragment)
        }

    }

    override fun getLayoutResId() = R.layout.fragment_appointment_enroll
    override val vm: AppointmentVM
        get() = ViewModelProvider(this).get(AppointmentVM::class.java)
}