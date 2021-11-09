package uz.medion.ui.main.user.payment

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentAddCardBinding
import uz.medion.ui.base.BaseFragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.medion.data.constants.Keys
import uz.medion.ui.main.user.appointment.EditTextListenerCardNumber
import uz.medion.ui.main.user.appointment.EditTextListenerExpireDate


class AddCardFragment : BaseFragment<FragmentAddCardBinding, PaymentVM>(),
    View.OnFocusChangeListener {

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

            findNavController().navigate(
                R.id.appointmentEnrollFragment,
                bundleOf(Pair(Keys.BUNDLE_APPOINTMENT_CARD_NUMBER, binding.etCardNumber.text.toString()))
            )
        }
    }

    override fun getLayoutResId() = R.layout.fragment_add_card
    override val vm: PaymentVM
        get() = ViewModelProvider(this).get(PaymentVM::class.java)

    override fun onFocusChange(p0: View?, hasFocus: Boolean) {
        if (hasFocus) {
            binding.etlCardNumber.hint = "card number"
        }
    }
}