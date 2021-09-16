package uz.medion.ui.splash.verification

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentVerificationBinding
import uz.medion.ui.base.BaseFragment

class VerificationFragment : BaseFragment<FragmentVerificationBinding, VerificationVM>() {


    override fun onBound() {
        Log.d("-------------", "onBound: hello")
    }

    override fun getLayoutResId() = R.layout.fragment_verification
    override val vm: VerificationVM
        get() = ViewModelProvider(this).get(VerificationVM::class.java)

}