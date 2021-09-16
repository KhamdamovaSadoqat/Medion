package uz.medion.ui.main

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.ActivityMainBinding
import uz.medion.ui.base.BaseActivity

class MainActivity: BaseActivity<ActivityMainBinding, MainVM>() {

    override fun onBound() {
        Log.d("-------------", "onBound: hello")
//        val intent = Intent(this, SplashActivity::class.java)
//        startActivity(intent)
//        finish()
    }

    @LayoutRes
    override fun getLayoutResId() = R.layout.fragment_log_in
    override val vm: MainVM by viewModels()

    override fun setStatusBarBackgroundHeight(statusBarBackground: View) {

    }
}