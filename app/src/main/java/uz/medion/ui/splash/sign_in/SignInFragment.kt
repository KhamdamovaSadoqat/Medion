package uz.medion.ui.splash.sign_in

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentSignInBinding
import uz.medion.ui.base.BaseFragment

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInVM>() {

    override fun onBound() {
        Log.d("-------------", "onBound: hello")
    }

    override fun getLayoutResId() = R.layout.fragment_sign_in
    override val vm: SignInVM
        get() = ViewModelProvider(this).get(SignInVM::class.java)

}