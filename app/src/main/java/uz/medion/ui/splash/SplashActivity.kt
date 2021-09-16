package uz.medion.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.databinding.ActivityMainBinding
import uz.medion.databinding.ActivitySplashBinding
import uz.medion.ui.base.BaseActivity

class SplashActivity: AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost = supportFragmentManager.findFragmentById(R.id.splash_nav_controller)
        if (navHost != null) {
            navController = navHost.findNavController()
        }
    }
}