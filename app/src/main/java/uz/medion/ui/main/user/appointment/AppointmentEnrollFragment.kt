package uz.medion.ui.main.user.appointment

import android.annotation.SuppressLint
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentAppointmentEnrollBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.DateTimeUtils
import uz.medion.utils.visible

class AppointmentEnrollFragment : BaseFragment<FragmentAppointmentEnrollBinding, AppointmentVM>() {

    val args: AppointmentEnrollFragmentArgs by navArgs()

    override fun onBound() {
        setUp()
    }

    @SuppressLint("SetTextI18n")
    fun setUp() {
        if (prefs.cardNumber != null) {
            Constants.cardNumber = prefs.cardNumber!!
            binding.tvCard.text = Constants.cardNumber
            binding.clCard.visible()
        }

        binding.tvFullName.text = args.doctorName
        binding.tvDataTime.text =
            "${DateTimeUtils.timeMillsToTextDate(args.appointmentDate)}, ${args.appointmentTime}"
        binding.tvConsultationType.text = args.appointmentType
        binding.chbCard.setOnClickListener {
            if(binding.chbCard.isChecked)
            binding.btnSubmit.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.fire_brick_900))
            else binding.btnSubmit.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.fire_brick_900_40))
        }
        binding.chbCash.setOnClickListener {
            if(binding.chbCash.isChecked)
                binding.btnSubmit.setBackgroundColor(ContextCompat.getColor(requireContext(),
                    R.color.fire_brick_900))
            else binding.btnSubmit.setBackgroundColor(ContextCompat.getColor(requireContext(),
                R.color.fire_brick_900_40))
        }
        binding.clAdd.setOnClickListener {
            val action =
                AppointmentEnrollFragmentDirections.actionAppointmentEnrollFragmentToAddCardFragment(
                    args.doctorName,
                    args.doctorId,
                    args.appointmentDate,
                    args.appointmentTime,
                    args.appointmentTypeId,
                    args.appointmentType
                )
            findNavController().navigate(action)
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