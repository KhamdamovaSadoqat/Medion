package uz.medion.ui.main.user.personalAccount

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.databinding.FragmentPersonalAccountBinding
import uz.medion.ui.base.BaseFragment

class PersonalAccountFragment : BaseFragment<FragmentPersonalAccountBinding, PersonalAccountVM>() {


    override fun onBound() {
        binding.cl1.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_changeNumberFragment)
        }
        binding.cl2.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_changePasswordFragment)
        }
    }

    override fun getLayoutResId() = R.layout.fragment_personal_account
    override val vm: PersonalAccountVM
        get() = ViewModelProvider(this).get(PersonalAccountVM::class.java)

}