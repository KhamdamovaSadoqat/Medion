package uz.medion.ui.splash.sign_up

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.medion.R
import uz.medion.data.model.RegistrationCreateRequest
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentSignUpBinding
import uz.medion.ui.base.BaseFragment

//registration
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
//                vm.getIsRegistrationFlowAvailable(binding.etNumber.rawText)
//                    .observe(this) { response ->
//                        when (response.status) {
//                            Status.LOADING -> {
//                            }
//                            Status.SUCCESS -> {
//                                Log.d("----------", "onBound: username is available")
//                            }
//                            Status.ERROR -> {
//                                Log.e("----------", "error: ${response.message}")
//                                binding.etNumber.error =
//                                    requireContext().getText(R.string.already_taken)
//                            }
//                        }
//                    }
                vm.registrationCreate(RegistrationCreateRequest("998${binding.etNumber.rawText}"))
                    .observe(this) { response ->
                        when (response.status) {
                            Status.LOADING -> {
                                Log.d("----------", "onBound: loading")
                            }
                            Status.SUCCESS -> {
                                prefs.isRegistered = true
                                prefs.password = binding.etPassword.text.toString().trim()
                                prefs.phoneNumber = binding.etNumber.text.toString().trim()

                                val action =
                                    SignUpFragmentDirections.actionSignUpFragmentToVerificationFragment(
                                        response.data!!.id,
                                        binding.etName.text.toString(),
                                        binding.etSurname.text.toString(),
                                        "998${binding.etNumber.rawText}",
                                        binding.etPassword.text.toString(),
                                        binding.etEmail.text.toString()
                                    )
                                findNavController().navigate(action)
                            }
                            Status.ERROR -> {
                                Log.d("----------", "onBound: ${response.message}")

                                if(response.message == requireContext().getString(R.string.user_is_registered)){
                                    Log.d("----------", "onBound: data: ${response.data}")
                                        binding.etNumber.error = requireContext().getString(R.string.user_is_registered)
                                }
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