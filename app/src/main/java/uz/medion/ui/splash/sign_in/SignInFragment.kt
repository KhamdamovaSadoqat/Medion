package uz.medion.ui.splash.sign_in

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.databinding.FragmentSignInBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.doctor.DoctorActivity

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInVM>() {

    override fun onBound() {
        Log.d("-------------", "onBound: dgshjiokfbognjvncfmsd")

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(requireContext(), DoctorActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }

    override fun getLayoutResId() = R.layout.fragment_sign_in
    override val vm: SignInVM
        get() = ViewModelProvider(this).get(SignInVM::class.java)

}