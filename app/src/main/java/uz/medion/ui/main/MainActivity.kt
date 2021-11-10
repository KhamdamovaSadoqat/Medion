package uz.medion.ui.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import uz.medion.R
import uz.medion.databinding.ActivityMainBinding
import uz.medion.ui.base.BaseActivity
import uz.medion.ui.main.user.IOnBackPressed
import uz.medion.utils.ViewUtils
import uz.medion.utils.gone
import uz.medion.utils.visible

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
            //from where bottom navigation should be removed
            if (destination.id == R.id.aboutDoctorFragment || destination.id == R.id.verificationFragment2) {
                ViewUtils.fadeOut(binding.partialBottomNav.root)
            } else {
                ViewUtils.fadeIn(binding.partialBottomNav.root)
            }
            //here goes HomeFragment
            if (destination.id == R.id.homeFragment) {
                ViewUtils.fadeIn(binding.ivMedion)
                ViewUtils.fadeIn(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivBackArrow)
                ViewUtils.fadeOut(binding.tvMain)
                ViewUtils.fadeOut(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivHeart)
                binding.partialBottomNav.bottomNavigationViewHome.visible()
            }
            //here goes OurDoctorsFragment
            if (destination.id == R.id.ourDoctorsFragment) {
                binding.tvMain.setText(R.string.our_doctors)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeIn(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                binding.partialBottomNav.bottomNavigationViewHome.visible()
                binding.ivSearch.setOnClickListener {
                    binding.tvMain.text = " "
                    ViewUtils.fadeIn(binding.ivNotification)
                    ViewUtils.fadeOut(binding.ivSearch)
                    binding.partialBottomNav.bottomNavigationViewHome.gone()

                    //   ViewUtils.fadeIn(binding.editSearch)

                    navController.navigate(
                        R.id.action_ourDoctorsFragment_to_searchViewFragment
                    )

                }
            }
            //here goes AboutDoctorsFragment
            if (destination.id == R.id.aboutDoctorFragment) {
                binding.tvMain.setText(R.string.our_doctors)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.ivHeart)
                ViewUtils.fadeOut(binding.tvMain)
                ViewUtils.fadeOut(binding.ivMedion)
                ViewUtils.fadeOut(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivNotification)
                binding.partialBottomNav.bottomNavigationViewHome.visible()
            }
            //here goes CertificateFragment
            if (destination.id == R.id.certificateFragment) {
                binding.tvMain.setText(R.string.image)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                ViewUtils.fadeOut(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivNotification)
                binding.partialBottomNav.bottomNavigationViewHome.visible()
            }
            //here goes PersonalAccountFragment
            if (destination.id == R.id.personalAccountFragment) {
                binding.tvMain.setText(R.string.personal_room)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.ivNotification)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                ViewUtils.fadeOut(binding.ivSearch)
                binding.partialBottomNav.bottomNavigationViewHome.visible()
            } else if (destination.id == R.id.personalAccountFragment ||
                destination.id == R.id.ourDoctorsFragment ||
                destination.id == R.id.esteticMedicineFragment ||
                destination.id == R.id.adressAndContactsFragment
            ) {
                ViewUtils.fadeIn(binding.ivBackArrow)
            } else if (destination.id == R.id.spaMedicineFragment) {
                ViewUtils.fadeOut(binding.ivBackArrow)
            }

            //here goes AboutDoctorsFragment
            if (destination.id == R.id.aboutDoctorFragment) {
                ViewUtils.fadeIn(binding.ivHeart)
                ViewUtils.fadeOut(binding.tvMain)
                ViewUtils.fadeOut(binding.ivMedion)
                ViewUtils.fadeOut(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivNotification)
                binding.partialBottomNav.bottomNavigationViewHome.gone()


            }
            //here goes EstethicMedicineFragment
            if (destination.id == R.id.esteticMedicineFragment) {
                binding.tvMain.setText(R.string.estethic_medicine)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeIn(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                binding.partialBottomNav.bottomNavigationViewHome.gone()
            }


            //here goes ChangeNumberFragment
            if (destination.id == R.id.changeNumberFragment) {
                binding.tvMain.setText(R.string.change_mobile_phone_number)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeOut(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                ViewUtils.fadeOut(binding.ivSearch)
                binding.partialBottomNav.bottomNavigationViewHome.gone()
            }
            //here goes ChangePasswordFragment
            if (destination.id == R.id.changePasswordFragment) {
                binding.tvMain.setText(R.string.change_password)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeOut(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                ViewUtils.fadeOut(binding.ivSearch)
                binding.partialBottomNav.bottomNavigationViewHome.gone()
            }
            //here goes ChooseLanguageFragment
            if (destination.id == R.id.chooseLanguageFragment) {
                binding.tvMain.setText(R.string.choose_language)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeOut(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                ViewUtils.fadeOut(binding.ivSearch)
                binding.partialBottomNav.bottomNavigationViewHome.gone()
            }
            //here goes PersonalDataFragment
            if (destination.id == R.id.personalDateFragment) {
                binding.tvMain.setText(R.string.personal_information)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeOut(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                ViewUtils.fadeOut(binding.ivSearch)
                binding.partialBottomNav.bottomNavigationViewHome.gone()
            }
            //here goes MyDoctorsFragment
            if (destination.id == R.id.myDoctorsFragment) {
                binding.tvMain.setText(R.string.my_doctors)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeOut(binding.ivNotification)
                ViewUtils.fadeIn(binding.ivSearch)
                binding.partialBottomNav.bottomNavigationViewHome.gone()


            }
            //here goes MyDocumentsFragment
            if (destination.id == R.id.myDocumentsFragment) {
                binding.tvMain.setText(R.string.my_documents)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeIn(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                binding.partialBottomNav.bottomNavigationViewHome.gone()
            }
            //here goes AddressAndContactsFragment
            if (destination.id == R.id.adressAndContactsFragment) {
                binding.tvMain.setText(R.string.adress_contact)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeIn(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                binding.partialBottomNav.bottomNavigationViewHome.visible()

            }
            //here goes AddressFragment
            if (destination.id == R.id.addressFragment) {
                binding.tvMain.setText(R.string.clinic_name)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeIn(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                binding.partialBottomNav.bottomNavigationViewHome.gone()
            }
            //here goes EstethicMedicineFragment
            if (destination.id == R.id.esteticMedicineFragment) {
                binding.tvMain.setText(R.string.estethic_medicine)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeIn(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
                binding.partialBottomNav.bottomNavigationViewHome.gone()
            }
            //here goes SpaMedicineFragment
            if (destination.id == R.id.spaMedicineFragment) {
                binding.tvMain.setText(R.string.spa_medicine)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeIn(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
            }
            //here goes SerachViewFragment
            if (destination.id == R.id.searchViewFragment) {
                binding.tvMain.text = " "
                ViewUtils.fadeIn(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivBackArrow)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.tvMain)
                ViewUtils.fadeOut(binding.ivMedion)
                binding.partialBottomNav.bottomNavigationViewHome.gone()
            }
            //here goes AppointmentEnrollFragment, AppointmentFragment
            if (destination.id == R.id.appointmentEnrollFragment || destination.id == R.id.appointmentFragment) {
                binding.tvMain.setText(R.string.constultation)
                ViewUtils.fadeIn(binding.ivBackArrow)
                ViewUtils.fadeIn(binding.tvMain)
                ViewUtils.fadeIn(binding.ivNotification)
                ViewUtils.fadeOut(binding.ivSearch)
                ViewUtils.fadeOut(binding.ivHeart)
                ViewUtils.fadeOut(binding.ivMedion)
            }

        }
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

    @SuppressLint("LogConditional")
    override fun onBackPressed() {
//        val navigationOptions =
//            NavOptions.Builder().setPopUpTo(navController.currentDestination!!.id, true).build()

//        if (navController.currentDestination!!.id == R.id.changeNumberFragment ||
//            navController.currentDestination!!.id == R.id.changePasswordFragment ||
//            navController.currentDestination!!.id == R.id.chooseLanguageFragment ||
//            navController.currentDestination!!.id == R.id.personalDateFragment ||
//            navController.currentDestination!!.id == R.id.myDoctorsFragment ||
//            navController.currentDestination!!.id == R.id.myDocumentsFragment
//        ) {
//            navController.popBackStack(R.id.personalAccountFragment, false)
        // navController.navigate(R.id.olympiadFragment, null, navigationOptions)
//        } else if (navController.currentDestination!!.id == R.id.addressFragment ||
//            navController.currentDestination!!.id == R.id.spaMedicineDetailsFragment ||
//            navController.currentDestination!!.id == R.id.ourDoctorsFragment ||
//            navController.currentDestination!!.id == R.id.aboutDoctorFragment ||
//            navController.currentDestination!!.id == R.id.esteticMedicineFragment ||
//            navController.currentDestination!!.id == R.id.adressAndContactsFragment ||
//            navController.currentDestination!!.id == R.id.spaMedicineFragment
//        ) {
//            navController.popBackStack()
//        } else
        if (navController.currentDestination!!.id == R.id.appointmentFragment ||
            navController.currentDestination!!.id == R.id.appointmentEnrollFragment ||
            navController.currentDestination!!.id == R.id.paymentCompleteFragment
        ) {
            navController.popBackStack(R.id.aboutDoctorFragment, false)
        } else if (navController.currentDestination!!.id == R.id.addCardFragment) {
            navController.popBackStack(R.id.appointmentEnrollFragment, false)
        } else {
            if (binding.dlMenu.isDrawerOpen(GravityCompat.START)) {
                binding.dlMenu.closeDrawer(GravityCompat.START)
            } else if (!bottomNavController.onBackPressed())
                bottomNavController.onBackPressed()
            super.onBackPressed()
        }
    }

}
