package uz.medion.ui.main.doctor

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.local.PrefsHelper
import uz.medion.databinding.ActivityDoctorBinding
import uz.medion.ui.base.BaseActivity

class DoctorActivity : BaseActivity<ActivityDoctorBinding,DoctorVM>() {
    private val TAG = "DoctorActivity"
    private var isFirstOpen = true
    private lateinit var navController: NavController

    override fun setStatusBarBackgroundHeight(statusBarBackground: View) {}

    override fun getLayoutResId()=R.layout.activity_doctor

    override fun onBound() {
        if (prefs.accessToken!=null){
            Constants.token=prefs.accessToken!!
            isFirstOpen=false
        } else{
            Constants.token=""
            isFirstOpen=true
        }
        setUp()
    }

    private fun setUp() {

        setContentView(binding.root)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.doctor_nav_controller)

        if (navHost != null) {
            navController = navHost.findNavController()
        }

        val bottomNavController: BottomNavigationView = findViewById(R.id.doctor_bottom_nav)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.searchViewFragment2, R.id.chatDoctorFragment,
                R.id.personalAreaFragment
            )
        )
        bottomNavController.setupWithNavController(navController)

    }

    override val vm: DoctorVM by viewModels()


}