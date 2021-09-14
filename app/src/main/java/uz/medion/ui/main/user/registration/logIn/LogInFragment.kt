package uz.medion.ui.main.user.registration.logIn

import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentLogInBinding
import uz.medion.ui.base.BaseFragment

class LogInFragment : BaseFragment<FragmentLogInBinding, LogInVM>() {

    override fun onBound() {

    }

    @LayoutRes
    override fun getLayoutResId() = R.layout.fragment_log_in
    override val vm: LogInVM
        get() = ViewModelProvider(this).get(LogInVM::class.java)
}