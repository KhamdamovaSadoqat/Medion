package uz.medion.ui.splash.verification

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentVerificationBinding
import uz.medion.ui.base.BaseFragment

class VerificationFragment : BaseFragment<FragmentVerificationBinding, VerificationVM>() {

    override fun onBound() {
        val st = requireArguments().getString(Keys.BUNDLE_CHANGE_PHONE_NUMBER)
        binding.btnSubmit.setOnClickListener {
            if (st.equals("changing")) {
                findNavController().navigate(R.id.personalAccountFragment)
            }
        }

    }

    override fun getLayoutResId() = R.layout.fragment_verification
    override val vm: VerificationVM
        get() = ViewModelProvider(this).get(VerificationVM::class.java)

}