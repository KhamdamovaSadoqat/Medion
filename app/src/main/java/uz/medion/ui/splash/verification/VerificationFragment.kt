package uz.medion.ui.splash.verification

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentVerificationBinding
import uz.medion.ui.base.BaseFragment
import `in`.aabhasjindal.otptextview.OTPListener
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import uz.medion.data.constants.Constants
import uz.medion.data.model.RegistrationRequest
import uz.medion.data.model.remote.Status
import uz.medion.ui.main.MainActivity

class VerificationFragment : BaseFragment<FragmentVerificationBinding, VerificationVM>() {

    private var changingNumber: String? = null
    private var requestId: String? = null
    private var registrationRequest: RegistrationRequest? = null
    internal val args: VerificationFragmentArgs by navArgs()

    override fun onBound() {
        if (arguments != null) {
            if (requireArguments().containsKey(Keys.BUNDLE_CHANGE_PHONE_NUMBER)) {
                changingNumber = requireArguments().getString(Keys.BUNDLE_CHANGE_PHONE_NUMBER)
                binding.btnSubmit.setOnClickListener {
                    if (changingNumber.equals("changing")) {
                        findNavController().navigate(R.id.personalAccountFragment)
                    }
                }
            } else {
                requestId = requireArguments().getString(Keys.BUNDLE_REQUEST_ID)
                binding.otpView.otpListener = object : OTPListener {
                    override fun onInteractionListener() {
                        // fired when user types something in the Otpbox
                    }
                    override fun onOTPComplete(otp: String) {
                        // fired when user has entered the OTP fully.

                        registrationRequest = RegistrationRequest(
                            args.email,
                            "${args.userName} ${args.userSurname}",
                            args.password,
                            args.phoneNumber
                        )

                        vm.verifyAndRegisterUser(args.requestId!!, otp, registrationRequest!!)
                            .observe(this@VerificationFragment) { response ->
                                when (response.status) {
                                    Status.LOADING -> { }
                                    Status.SUCCESS -> {
                                        Constants.token =
                                            response.data!!.accessToken.toString()

                                        binding.btnSubmit.background = ContextCompat.getDrawable(
                                            requireContext(),
                                            R.drawable.bg_red_8
                                        )
                                        val intent = Intent(requireContext(), MainActivity::class.java)
                                        startActivity(intent)
                                        requireActivity().finish()
                                    }
                                    Status.ERROR -> { }
                                }
                            }
                    }
                }
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_verification
    override val vm: VerificationVM
        get() = ViewModelProvider(this).get(VerificationVM::class.java)
}
