package uz.medion.ui.main

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import uz.medion.R
import uz.medion.data.constants.Constants.ADMIN_PHONE_NUMBER
import uz.medion.databinding.ActivityMainBinding
import uz.medion.ui.base.BaseActivity
import uz.medion.utils.ViewUtils

class MainActivity : BaseActivity<ActivityMainBinding, MainVM>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavController: BottomNavController
    private lateinit var navController: NavController

    override fun onBound() {
        setUp()
    }

    private fun setUp() {
        val navHost = supportFragmentManager.findFragmentById(R.id.main_nav_controller)
        if (navHost != null) {
            navController = navHost.findNavController()
        }
        bottomNavController =
            BottomNavController(binding, binding.partialBottomNav, this, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->

            //where bottom navigation should be removed
            if (destination.id == R.id.aboutDoctorFragment ||
                destination.id == R.id.verificationFragment2 ||
                destination.id == R.id.changeNumberFragment ||
                destination.id == R.id.changePasswordFragment ||
                destination.id == R.id.chooseLanguageFragment ||
                destination.id == R.id.myDocumentsFragment ||
                destination.id == R.id.personalDateFragment ||
                destination.id == R.id.esteticMedicineFragment ||
                destination.id == R.id.chatFragment
            ) ViewUtils.fadeOut(binding.partialBottomNav.root)
            else ViewUtils.fadeIn(binding.partialBottomNav.root)

            //where arrow_back and text should be removed medion logo should be displayed
            if (destination.id == R.id.homeFragment) {
                ViewUtils.fadeOut(binding.tvMain)
                ViewUtils.fadeOut(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.ivMedion)
            } else {
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeOut(binding.ivMedion)
            }

            //where search should be displayed
            if (destination.id == R.id.ourDoctorsFragment ||
                destination.id == R.id.myDoctorsFragment ||
                destination.id == R.id.myDocumentsFragment ||
                destination.id == R.id.addressFragment ||
                destination.id == R.id.adressAndContactsFragment
            ) ViewUtils.fadeIn(binding.ivSearch)
            else ViewUtils.fadeOut(binding.ivSearch)

            //where notification should be displayed
            if (destination.id == R.id.homeFragment ||
                destination.id == R.id.personalAccountFragment ||
                destination.id == R.id.esteticMedicineFragment ||
                destination.id == R.id.spaMedicineFragment ||
                destination.id == R.id.appointmentEnrollFragment ||
                destination.id == R.id.appointmentFragment
            ) ViewUtils.fadeIn(binding.ivNotification)
            else ViewUtils.fadeOut(binding.ivNotification)

            //where heart should be displayed
            if (destination.id == R.id.aboutDoctorFragment) ViewUtils.fadeIn(binding.ivHeart)
            else ViewUtils.fadeOut(binding.ivHeart)

            //where call icon should be displayed
            if (destination.id == R.id.chatFragment) {
                ViewUtils.fadeIn(binding.profileAdmin)
                ViewUtils.fadeIn(binding.ivCall)
                binding.ivCall.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = (Uri.fromParts("tel", ADMIN_PHONE_NUMBER, null))
                    startActivity(intent)
                }
            } else {
                ViewUtils.fadeOut(binding.ivCall)
                ViewUtils.fadeOut(binding.profileAdmin)
            }

            when (destination.id) {
                R.id.adressAndContactsFragment -> binding.tvMain.setText(R.string.adress_contact)
                R.id.appointmentEnrollFragment -> binding.tvMain.setText(R.string.constultation)
                R.id.esteticMedicineFragment -> binding.tvMain.setText(R.string.estethic_medicine)
                R.id.personalAccountFragment -> binding.tvMain.setText(R.string.personal_room)
                R.id.changePasswordFragment -> binding.tvMain.setText(R.string.change_password)
                R.id.chooseLanguageFragment -> binding.tvMain.setText(R.string.choose_language)
                R.id.changeNumberFragment -> binding.tvMain.setText(R.string.change_mobile_phone_number)
                R.id.personalDateFragment -> binding.tvMain.setText(R.string.personal_information)
                R.id.appointmentFragment -> binding.tvMain.setText(R.string.constultation)
                R.id.myDocumentsFragment -> binding.tvMain.setText(R.string.my_documents)
                R.id.spaMedicineFragment -> binding.tvMain.setText(R.string.spa_medicine)
                R.id.aboutDoctorFragment -> binding.tvMain.setText(R.string.about_doctor)
                R.id.certificateFragment -> binding.tvMain.setText(R.string.image)
                R.id.ourDoctorsFragment -> binding.tvMain.setText(R.string.our_doctors)
                R.id.myDoctorsFragment -> binding.tvMain.setText(R.string.my_doctors)
                R.id.addressFragment -> binding.tvMain.setText(R.string.adress)
                R.id.addCardFragment -> binding.tvMain.setText(R.string.add_card)
                R.id.chatFragment -> binding.tvMain.setText(R.string.anastasiya)
            }
        }

        binding.ivSearch.setOnClickListener { navController.navigate(R.id.searchViewFragment) }
        binding.ivBackArrow.setOnClickListener { onBackPressed() }

        binding.nvNavigationDrawer.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> navController.navigate(R.id.homeFragment)
                R.id.nav_our_doctors -> navController.navigate(R.id.ourDoctorsFragment)
                R.id.nav_personal_account -> navController.navigate(R.id.personalAccountFragment)
                R.id.nav_adress_contact -> navController.navigate(R.id.adressAndContactsFragment)
                R.id.nav_estethic_medicine -> navController.navigate(R.id.esteticMedicineFragment)
                R.id.nav_spa_medicine -> navController.navigate(R.id.spaMedicineFragment)
            }
            binding.dlMenu.closeDrawer(GravityCompat.START)
            true
        }
    }

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main
    override val vm: MainVM by viewModels()

    override fun setStatusBarBackgroundHeight(statusBarBackground: View) {}

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
//        val navigationOptions =
//            NavOptions.Builder().setPopUpTo(navController.currentDestination!!.id, true).build()
//        navController.navigate(R.id.olympiadFragment, null, navigationOptions)
        if (navController.currentDestination!!.id == R.id.paymentCompleteFragment) {
            navController.popBackStack(R.id.aboutDoctorFragment, false)
        } else{
            if (binding.dlMenu.isDrawerOpen(GravityCompat.START)) {
                binding.dlMenu.closeDrawer(GravityCompat.START)
            } else if (!bottomNavController.onBackPressed())
                bottomNavController.onBackPressed()
            super.onBackPressed()
        }
    }
}