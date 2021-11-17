package uz.medion.ui.splash.verification

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentVerificationBinding
import uz.medion.ui.base.BaseFragment

import `in`.aabhasjindal.otptextview.OTPListener
import android.util.Log
import uz.medion.ui.main.MainActivity


class VerificationFragment : BaseFragment<FragmentVerificationBinding, VerificationVM>() {

    override fun onBound() {
        if (arguments != null) {
            if (requireArguments().containsKey(Keys.BUNDLE_CHANGE_PHONE_NUMBER)) {
                val st = requireArguments().getString(Keys.BUNDLE_CHANGE_PHONE_NUMBER)
                binding.btnSubmit.setOnClickListener {
                    if (st.equals("changing")) {
                        findNavController().navigate(R.id.personalAccountFragment)
                    }
                }
            }
        } else {

            binding.otpView.otpListener = object : OTPListener {
                override fun onInteractionListener() {
                    // fired when user types something in the Otpbox
                    Log.d("----------", "onInteractionListener: ")
                }

                override fun onOTPComplete(otp: String) {
                    Log.d("----------", "onOTPComplete: $otp")
                    // fired when user has entered the OTP fully.
                }
            }


//            private OtpView otpView;
//            otpView = findViewById(R.id.otp_view);
//            otpView.setListener(new OnOtpCompletionListener() {
//                @Override public void onOtpCompleted(String otp) {
//
//                    // do Stuff
//                    Log.d("onOtpCompleted=>", otp);
//                }
//            });

            binding.btnSubmit.setOnClickListener {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_verification
    override val vm: VerificationVM
        get() = ViewModelProvider(this).get(VerificationVM::class.java)

}
