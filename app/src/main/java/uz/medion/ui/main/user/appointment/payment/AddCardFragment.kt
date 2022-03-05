package uz.medion.ui.main.user.appointment.payment

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.medion.R
import uz.medion.databinding.FragmentAddCardBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.appointment.AppointmentEnrollFragmentArgs
import uz.medion.ui.main.user.appointment.EditTextListenerCardNumber
import uz.medion.ui.main.user.appointment.EditTextListenerExpireDate


class AddCardFragment : BaseFragment<FragmentAddCardBinding, PaymentVM>(),
    View.OnFocusChangeListener {

    val args: AddCardFragmentArgs by navArgs()

    override fun onBound() {
        setUp()
    }

    @SuppressLint("LogConditional")
    fun setUp() {
        binding.etCardNumber.addTextChangedListener(EditTextListenerCardNumber)
        binding.etCardNumber.onFocusChangeListener = this
        binding.etMmYy.addTextChangedListener(EditTextListenerExpireDate)
        binding.btnAdd.setOnClickListener {
            prefs.cardNumber = binding.etCardNumber.text.toString()
            prefs.cardNumberExpireDate = binding.etMmYy.text.toString()
            val action =
                AddCardFragmentDirections.actionAddCardFragmentToAppointmentEnrollFragment(
                    args.doctorName,
                    args.doctorId,
                    args.appointmentDate,
                    args.appointmentTime,
                    args.appointmentType,
                    args.appointmentTypeId,
                    binding.etCardNumber.text.toString())
            findNavController().navigate(action)
        }
    }

    override fun getLayoutResId() = R.layout.fragment_add_card
    override val vm: PaymentVM
        get() = ViewModelProvider(this)[PaymentVM::class.java]

    override fun onFocusChange(p0: View?, hasFocus: Boolean) {
        if (hasFocus) {
            binding.etlCardNumber.hint = "card number"
        }
    }
}