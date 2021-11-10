package uz.medion.ui.main.user.payment

import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentPaymentCompleteBinding
import uz.medion.ui.base.BaseFragment

class PaymentCompleteFragment : BaseFragment<FragmentPaymentCompleteBinding, PaymentVM>() {

    override fun onBound() {

    }

    override fun getLayoutResId() = R.layout.fragment_payment_complete
    override val vm: PaymentVM
        get() = ViewModelProvider(this).get(PaymentVM::class.java)
}