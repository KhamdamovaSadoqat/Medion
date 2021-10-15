package uz.medion.ui.splash.sign_up

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentSignUpBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.MainActivity


class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpVM>() {


    override fun onBound() {
//        binding.btnSignIn.setOnClickListener {
//            val intent = Intent(requireContext(), MainActivity::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//        }
//        binding.btnSignUp.setOnClickListener {
//            val intent = Intent(requireContext(), MainActivity::class.java)
//            startActivity(intent)
//            requireActivity().finish()
//        }
    }

    override fun getLayoutResId() = R.layout.fragment_sign_up
    override val vm: SignUpVM
        get() = ViewModelProvider(this).get(SignUpVM::class.java)

}