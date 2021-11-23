package uz.medion.ui.main.doctor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.medion.R
import uz.medion.databinding.ActivityDoctorBinding

class DoctorActivity : AppCompatActivity() {
    private val TAG = "DoctorActivity"
    private lateinit var binding: ActivityDoctorBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
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


}