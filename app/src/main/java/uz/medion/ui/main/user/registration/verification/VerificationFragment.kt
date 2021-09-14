package uz.medion.ui.main.user.registration.verification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentVerificationBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.registration.logIn.LogInVM

class VerificationFragment : BaseFragment<FragmentVerificationBinding, VerificationVM>() {


    override fun onBound() {
    }

    override fun getLayoutResId() = R.layout.fragment_verification
    override val vm: VerificationVM
        get() = ViewModelProvider(this).get(VerificationVM::class.java)

}