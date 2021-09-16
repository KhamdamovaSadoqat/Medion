package uz.medion.ui.splash.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentSignUpBinding
import uz.medion.ui.base.BaseFragment


class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpVM>() {


    override fun onBound() {
        TODO("Not yet implemented")
    }

    override fun getLayoutResId() = R.layout.fragment_sign_up
    override val vm: SignUpVM
        get() = ViewModelProvider(this).get(SignUpVM::class.java)

}