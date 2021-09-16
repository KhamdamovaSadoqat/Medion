package uz.medion.ui.splash

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.ActivitySplashBinding
import uz.medion.ui.base.BaseActivity

class SplashActivity: BaseActivity<ActivitySplashBinding, SplashVM>() {


    override fun onBound() {
        Log.d("-------------", "onBound: hello")
    }

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_splash
    override val vm: SplashVM by viewModels()

    override fun setStatusBarBackgroundHeight(statusBarBackground: View) {

    }
}