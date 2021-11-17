package uz.medion.ui.splash.sign_up

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.medion.R
import uz.medion.databinding.FragmentSignUpBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.appointment.EditTextListenerCardNumber
import uz.medion.ui.main.user.appointment.EditTextListenerExpireDate

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpVM>(), View.OnFocusChangeListener {

    private val args: SignUpFragmentArgs by navArgs()

    override fun onBound() {

        if(arguments != null){
            binding.etName.setText(args.userName)
            binding.etSurname.setText(args.userSurname)
        }

        binding.etNumber.addTextChangedListener(EditTextListenerPhoneNumber)
        binding.etNumber.onFocusChangeListener = this

        binding.btnSubmit.setOnClickListener {
            findNavController().navigate(R.id.verificationFragment)
        }

    }

    override fun getLayoutResId() = R.layout.fragment_sign_up
    override val vm: SignUpVM
        get() = ViewModelProvider(this).get(SignUpVM::class.java)

    @SuppressLint("SetTextI18n")
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (hasFocus) {
            binding.etNumber.setText("+998 ")
        }
    }

}