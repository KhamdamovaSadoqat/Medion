package uz.medion.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import uz.medion.R
import uz.medion.databinding.PartialBottomNavigationBinding
import uz.medion.utils.ViewUtils
import uz.medion.utils.showToast

class BottomNavController(
    private val binding: PartialBottomNavigationBinding,
    private val activity: AppCompatActivity,
    private val navController: NavController
) {

    private var oldClickedItemPos = 0
    private var lastClickedItemPos = 0
    private lateinit var navOptions: NavOptions
    private var lastBackClickTime = 0L

    init {
        controlBottomNav()
    }

    private fun controlBottomNav() {
        binding.apply {
            cl1.setOnClickListener {

                lastClickedItemPos = 0
                handleClick()

            }
            cl2.setOnClickListener {

                lastClickedItemPos = 1
                handleClick()

            }
            cl3.setOnClickListener {

                lastClickedItemPos = 2
                handleClick()

            }
        }
    }

    private fun handleClick() {
        makeDefaultItem()
        highlightItem()
    }

    private fun makeDefaultItem() {
        binding.apply {
            when (oldClickedItemPos) {
                0 -> {
                    ViewUtils.fadeOut(view1, 200)
                }
                1 -> {
                    ViewUtils.fadeOut(view2, 200)
                }
                2 -> {
                    ViewUtils.fadeOut(view3, 200)
                }
            }
        }

    }

    private fun highlightItem() {
        binding.apply {
            when (lastClickedItemPos) {
                0 -> {
                    navOptions = NavOptions.Builder()
                        .setLaunchSingleTop(true)
                        .setPopUpTo(R.id.homeFragment, true)
                        .build()
                    ViewUtils.fadeIn(view1, 200)
//                    navController.popBackStack(R.id.homeFragment, false)
                    navController.navigate(R.id.homeFragment, bundleOf(), navOptions)
                    return@apply
                }
                1 -> {
                    navOptions = NavOptions.Builder()
                        .setLaunchSingleTop(true)
//                        .setPopUpTo(R.id.ratingFragment, true)
                        .build()
                    ViewUtils.fadeIn(view2, 200)
//                    navController.popBackStack(R.id.ratingFragment, false)
//                    navController.navigate(R.id.ratingFragment, bundleOf(), navOptions)
                    return@apply
                }
                2 -> {
                    navOptions = NavOptions.Builder()
                        .setLaunchSingleTop(true)
//                        .setPopUpTo(R.id.omadFragment, true)
                        .build()
                    ViewUtils.fadeIn(view3, 200)
//                    navController.navigate(R.id.omadFragment, bundleOf(), navOptions)
//                    navController.popBackStack(R.id.omadFragment, true)
                    return@apply
                }
            }
        }
        oldClickedItemPos = lastClickedItemPos
    }

    fun onBackPressed(): Boolean {
        val currentDest = navController.currentDestination?.id
        if (currentDest == R.id.homeFragment) {
            Log.d("--------------", "onBackPerssed: called")
            val currentTime = System.currentTimeMillis()

            if (lastBackClickTime + 2000 > currentTime) {
                activity.finish()
            } else {
                activity.showToast(activity.getString(R.string.on_back_pressed))
                lastBackClickTime = currentTime
            }
            return true
        }
        return false
    }


}