package uz.medion.ui.main.user.registration.logIn

import android.content.Intent
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentLogInBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.splash.SplashActivity

class LogInFragment : BaseFragment<FragmentLogInBinding, LogInVM>() {

    override fun onBound() {
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(requireContext(), SplashActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    @LayoutRes
    override fun getLayoutResId() = R.layout.fragment_log_in
    override val vm: LogInVM
        get() = ViewModelProvider(this).get(LogInVM::class.java)
}