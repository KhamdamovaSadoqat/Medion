package uz.medion.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.databinding.ActivityMainBinding

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