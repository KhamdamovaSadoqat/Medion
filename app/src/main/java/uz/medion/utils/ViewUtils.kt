package uz.medion.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.util.Base64
import android.view.*
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

object ViewUtils {
    /**
     * Change [StatusBar] to LightStatusBar programmatically
     *
     * @param activity
     * @param color is new color which set for background if [StatusBar]
     */
    fun setLightStatusBar(activity: Activity, @ColorRes color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = activity.window.decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.decorView.systemUiVisibility = flags
            activity.window.statusBarColor = ContextCompat.getColor(activity, color)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = Color.BLACK
        }
    }

    /**
     * Make [StatusBar] to default.
     *
     * @param activity
     * @param color is new color which set for background if [StatusBar]
     */
    fun clearLightStatusBar(activity: Activity, @ColorRes colorRes: Int? = null, color:Int? = null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window: Window = activity.window
            var flags: Int = window.decorView.getSystemUiVisibility()
            flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            window.decorView.setSystemUiVisibility(flags)
            if (colorRes != null)
                window.statusBarColor = ContextCompat
                    .getColor(activity, colorRes)
            else
                window.statusBarColor = color!!
        }
    }


    /**
     * Finds and returns [StatusBar] height. Returns 0 if [StatusBar] height not found.
     *
     * @param activity
     */
    fun getStatusBarHeight(activity: Activity): Int {
        // status bar height
        var statusBarHeight = 0
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = activity.resources.getDimensionPixelSize(resourceId)
        }

        return statusBarHeight
    }

    /**
     * Changes the height of the view with animation.
     *
     * @param view ViewGroup of which height needs to be changed
     * @param newHeightInDp new height in DP
     */
    fun changeHeightWithAnimation(view: ViewGroup, newHeightInDp: Int) {

        TransitionManager.beginDelayedTransition(
            view,
            TransitionSet().addTransition(ChangeBounds())
        )

        val params = view.layoutParams
        params.height = newHeightInDp.toPx()
        view.layoutParams = params
    }

    /**
     * Generates Bitmap from string which is formatted in base64
     *
     * @param base64String String which is formatted in base64
     * @return
     */
    fun generateBitmapFromBase64String(base64String: String): Bitmap {
        val base64Image = base64String.split(",").toTypedArray()[1]
        val decodedString: ByteArray = Base64.decode(base64Image, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    /**
     * Makes view visible with fade in animation.
     *
     * @param view view which needs to be visible
     * @param duration duration of animation
     */
    fun fadeIn(view: View, duration: Long = 300) {
        if (view.visibility != View.VISIBLE) {
            val fadeIn = AlphaAnimation(0f, 1f)
            fadeIn.interpolator = AccelerateInterpolator()
            fadeIn.duration = duration
            view.animation = fadeIn
        }
        view.visible()
    }

    /**
     * Makes view gone with fade out animation.
     *
     * @param view view which needs to be gone
     * @param duration duration of animation
     */
    fun fadeOut(view: View, duration: Long = 300) {
        if (view.visibility == View.VISIBLE) {
            val fadeIn = AlphaAnimation(1f, 0f)
            fadeIn.interpolator = DecelerateInterpolator()
            fadeIn.duration = duration
            view.animation = fadeIn
        }

        view.gone()
    }

    /**
     * Hides keyboard that was opened from a dialog.
     *
     * Reason: There might be need to hide the keyboard and return user's focus to dialog again. In such cases,
     * if keyboard is open, it should be hidden so that user focuses on dialog again.
     */
    fun hideDialogKeyboard(dialog: Dialog, context: Context) {
        dialog.currentFocus?.let {
            it.clearFocus()
            val imm: InputMethodManager =
                context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    /**
     * Returns device screen size.
     *
     * @param size an array of height and width
     * @param width of device
     * @param height of device
     */
    fun screenDimention(context: Context): ArrayList<Int> {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width: Int
        val height: Int
        val size = arrayListOf<Int>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val windowMetrics = wm.currentWindowMetrics
            val windowInsets: WindowInsets = windowMetrics.windowInsets

            val insets = windowInsets.getInsetsIgnoringVisibility(
                WindowInsets.Type.navigationBars() or WindowInsets.Type.displayCutout()
            )
            val insetsWidth = insets.right + insets.left
            val insetsHeight = insets.top + insets.bottom

            val b = windowMetrics.bounds
            width = b.width() - insetsWidth
            height = b.height() - insetsHeight
            size.add(width)
            size.add(height)
        } else {
            val point = Point()
            val display = wm.defaultDisplay // deprecated in API 30
            display?.getSize(point) // deprecated in API 30
            width = point.x
            height = point.y
            size.add(width)
            size.add(height)
        }
        return size
    }

    fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }
}