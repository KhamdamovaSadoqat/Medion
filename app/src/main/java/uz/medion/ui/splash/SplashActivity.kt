package uz.medion.ui.splash

import android.annotation.SuppressLint
import android.view.View
import androidx.activity.viewModels
import uz.medion.R
import uz.medion.databinding.ActivitySplashBinding
import uz.medion.ui.base.BaseActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity: BaseActivity<ActivitySplashBinding, SplashVM>() {

    override fun onBound() {
    }
//    private lateinit var navController: NavController
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySplashBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        val navHost = supportFragmentManager.findFragmentById(R.id.splash_nav_controller)
//        if (navHost != null) {
//            navController = navHost.findNavController()
//        }
//    }

    override fun setStatusBarBackgroundHeight(statusBarBackground: View) {}

    override fun getLayoutResId() = R.layout.activity_splash

    override val vm: SplashVM by viewModels()
}