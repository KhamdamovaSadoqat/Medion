package uz.medion.ui.splash

import android.annotation.SuppressLint
import android.view.View
import androidx.activity.viewModels
import uz.medion.R
import uz.medion.databinding.ActivitySplashBinding
import uz.medion.ui.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity<ActivitySplashBinding, SplashVM>() {

    override fun onBound() {}

    override fun setStatusBarBackgroundHeight(statusBarBackground: View) {}

    override fun getLayoutResId() = R.layout.activity_splash

    override val vm: SplashVM by viewModels()
}