package uz.medion.ui.main.user.appointment

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentAppointmentEnrollBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.DateTimeUtils
import java.util.*

class AppointmentEnrollFragment : BaseFragment<FragmentAppointmentEnrollBinding, AppointmentVM>() {

    val args: AppointmentEnrollFragmentArgs by navArgs()
    private var doctorName: String = ""
    private var date: String = ""
    private var time: String = ""
    private var type: String = ""
    private var card: String = ""

    override fun onBound() {
        time = args.appointmentTime
        doctorName = args.doctorName
        type = args.appointmentType
        date = DateTimeUtils.timeMillsToTextDate(args.appointmentDate) // need to give date in correct form
        setUp()

    }

    @SuppressLint("SetTextI18n")
    fun setUp() {
        Constants.cardNumber = prefs.cardNumber ?: "UZCARD"
        binding.tvCard.text = Constants.cardNumber

        binding.tvFullName.text = doctorName
        binding.tvDataTime.text = "$date, $time"
        binding.tvConsultationType.text = type

        if (requireArguments().containsKey(Keys.BUNDLE_APPOINTMENT_CARD_NUMBER)) {
            card =
                requireArguments().getString(Keys.BUNDLE_APPOINTMENT_CARD_NUMBER, "123") as String
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
        get() = ViewModelProvider(this)[AppointmentVM::class.java]
}