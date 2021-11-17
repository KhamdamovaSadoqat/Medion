package uz.medion.ui.splash.verification

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentVerificationBinding
import uz.medion.ui.base.BaseFragment
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
