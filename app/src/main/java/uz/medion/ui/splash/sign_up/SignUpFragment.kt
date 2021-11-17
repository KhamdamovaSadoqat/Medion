package uz.medion.ui.splash.sign_up

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentSignUpBinding
import uz.medion.ui.base.BaseFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpVM>(), View.OnFocusChangeListener {

    private val args: SignUpFragmentArgs by navArgs()

    @SuppressLint("LogConditional")
    override fun onBound() {

        if(arguments != null){
            binding.etName.setText(args.userName)
            binding.etSurname.setText(args.userSurname)
        }

        binding.etNumber.addTextChangedListener(EditTextListenerPhoneNumber)
        binding.etNumber.onFocusChangeListener = this

        //checking wether user is register or not
        //throwing error as "number already taken" is user is registered
        binding.btnSubmit.setOnClickListener {
            Log.d("----------", "onBound: number: ${binding.etNumber}")
            vm.getIsRegistrationFlowAvailable(binding.etNumber.toString()).observe(this) { response ->
                when (response.status) {
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        Log.d("----------", "onBound: username is available")
                    }
                    Status.ERROR -> {
                        Log.e("----------", "error: ${response.message}")
                        binding.etNumber.error = requireContext().getText(R.string.already_taken)
                        response.throwable?.let { handleError(it) }
                    }
                }
            }
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