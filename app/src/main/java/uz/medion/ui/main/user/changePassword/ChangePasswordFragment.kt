package uz.medion.ui.main.user.changePassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.databinding.FragmentChangePasswordBinding
import uz.medion.ui.base.BaseFragment

class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding, ChangePasswordVM>() {

    override fun onBound() {
        binding.btnSubmit.setOnClickListener {
            findNavController().navigate(R.id.personalAccountFragment)
        }
    }

    override fun getLayoutResId() = R.layout.fragment_change_password
    override val vm: ChangePasswordVM
        get() = ViewModelProvider(this).get(ChangePasswordVM::class.java)
}