package uz.medion.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
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
import uz.medion.ui.base.BaseActivity
import uz.medion.ui.splash.SplashActivity

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHost = supportFragmentManager.findFragmentById(R.id.main_nav_controller)
        if (navHost != null) {
            navController = navHost.findNavController()
        }
    }




//    override fun onBound() {
//        Log.d("-------------", "onBound: hello")
////        val intent = Intent(this, SplashActivity::class.java)
////        startActivity(intent)
////        finish()
//    }
//
//    @LayoutRes
//    override fun getLayoutResId() = R.layout.fragment_log_in
//    override val vm: MainVM by viewModels()
//
//    override fun setStatusBarBackgroundHeight(statusBarBackground: View) {
//
//    }


}