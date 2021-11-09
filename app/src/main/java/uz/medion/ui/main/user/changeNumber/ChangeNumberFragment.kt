package uz.medion.ui.main.user.changeNumber

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentChangeNumberBinding
import uz.medion.ui.base.BaseFragment

class ChangeNumberFragment : BaseFragment<FragmentChangeNumberBinding, ChangeNumberVM>() {


    override fun onBound() {
        binding.btnSubmit.setOnClickListener {
            findNavController().navigate(
                R.id.verificationFragment2,
                bundleOf(Keys.BUNDLE_CHANGE_PHONE_NUMBER to "changing")
            ) }
    }


    override fun getLayoutResId() = R.layout.fragment_change_number
    override val vm: ChangeNumberVM
        get() = ViewModelProvider(this).get(ChangeNumberVM::class.java)

}