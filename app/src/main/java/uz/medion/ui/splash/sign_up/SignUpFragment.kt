package uz.medion.ui.splash.sign_up

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentSignUpBinding
import uz.medion.ui.base.BaseFragment

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpVM>() {

    private val args: SignUpFragmentArgs by navArgs()

    @SuppressLint("LogConditional")
    override fun onBound() {

        //while signining via google name and surname comes via argument
        if (arguments != null) {
            binding.etName.setText(args.userName)
            binding.etSurname.setText(args.userSurname)
        }

        //checking weather user is register or not
        //throwing error as "number already taken" is user is registered
        binding.btnSubmit.setOnClickListener {
            // checking where user complete all fields correctly
            if (checkAllFields()) {
                vm.getIsRegistrationFlowAvailable(binding.etNumber.rawText)
                    .observe(this) { response ->
                        when (response.status) {
                            Status.LOADING -> {
                            }
                            Status.SUCCESS -> {
                                Log.d("----------", "onBound: username is available")
                            }
                            Status.ERROR -> {
                                Log.e("----------", "error: ${response.message}")
                                binding.etNumber.error =
                                    requireContext().getText(R.string.already_taken)
                                response.throwable?.let { handleError(it) }
                            }
                        }
                    }

                vm.getResponseOfRequestEmail(binding.etEmail.toString()).observe(this) { response ->
                    when (response.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            Log.d("----------", "onBound: username is available")
                            Log.d("----------", "onBound: response.data: ${response.data}")
                            Log.d("----------", "onBound: response.message: ${response.message}")
                            Log.d("----------", "onBound: response.status: ${response.status}")
                            Log.d(
                                "----------",
                                "onBound: response.throwable: ${response.throwable}"
                            )
                        }
                        Status.ERROR -> {
                            Log.e("----------", "error email: ${response.message}")
//                        response.throwable?.let { handleError(it) }
                        }
                    }
                }
            }
        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.etName.length() == 0) {
            binding.etName.error =
                requireContext().getText(R.string.required_field)
            return false
        } else binding.etName.error = null
        if (binding.etSurname.length() == 0) {
            binding.etSurname.error =
                requireContext().getText(R.string.required_field)
            return false
        } else binding.etSurname.error = null
        if (binding.etNumber.rawText.length != 9) {
            binding.etNumber.error =
                requireContext().getText(R.string.invalid_phone_number)
            return false
        } else binding.etNumber.error = null
        if (binding.etEmail.length() == 0 || android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.toString())
                .matches()
        ) {
            binding.etEmail.error = requireContext().getText(R.string.invalid_email)
            return false
        } else binding.etEmail.error = null

        return true
    }

    override fun getLayoutResId() = R.layout.fragment_sign_up
    override val vm: SignUpVM
        get() = ViewModelProvider(this).get(SignUpVM::class.java)


}