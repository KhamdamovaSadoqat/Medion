package uz.medion.ui.main.user.appointment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentAppointmentEnrollBinding
import uz.medion.ui.base.BaseFragment

class AppointmentEnrollFragment : BaseFragment<FragmentAppointmentEnrollBinding, AppointmentVM>() {

    private var doctorName: String = ""
    private var date: String = ""
    private var time: String = ""
    private var type: String = ""
    private var card: String = ""

    override fun onBound() {
        setUp()
    }

    @SuppressLint("SetTextI18n")
    fun setUp() {
        Constants.cardNumber = prefs.cardNumber ?: "UZCARD"
        binding.tvCard.text = Constants.cardNumber
        if (requireArguments().containsKey(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME) &&
            requireArguments().containsKey(Keys.BUNDLE_APPOINTMENT_DATE) &&
            requireArguments().containsKey(Keys.BUNDLE_APPOINTMENT_TIME) &&
            requireArguments().containsKey(Keys.BUNDLE_APPOINTMENT_TYPE)
        ) {
            doctorName = requireArguments().get(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME) as String
            date = requireArguments().get(Keys.BUNDLE_APPOINTMENT_DATE) as String
            time = requireArguments().get(Keys.BUNDLE_APPOINTMENT_TIME) as String
            type = requireArguments().get(Keys.BUNDLE_APPOINTMENT_TYPE) as String

            binding.tvFullName.text = doctorName
            binding.tvDataTime.text = "$date, $time"
            binding.tvConsultationType.text = type
        }
        if (requireArguments().containsKey(Keys.BUNDLE_APPOINTMENT_CARD_NUMBER)) {
            card = requireArguments().getString(Keys.BUNDLE_APPOINTMENT_CARD_NUMBER, "123") as String
            binding.tvCard.text = card
            Constants.cardNumber = prefs.cardNumber ?: "UZCARD"
        }

        binding.chbCard.setOnClickListener {
            binding.chbCash.isChecked = false
        }
        binding.chbCash.setOnClickListener {
            binding.chbCard.isChecked = false
        }

        binding.clAdd.setOnClickListener {
            findNavController().navigate(R.id.action_appointmentEnrollFragment_to_addCardFragment)
        }
        binding.btnSubmit.setOnClickListener {
            if (binding.chbCard.isChecked)
                findNavController().navigate(R.id.action_appointmentEnrollFragment_to_payment_complete)
        }
    }

    override fun getLayoutResId() = R.layout.fragment_appointment_enroll
    override val vm: AppointmentVM
        get() = ViewModelProvider(this).get(AppointmentVM::class.java)
}