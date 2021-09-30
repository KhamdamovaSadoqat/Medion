package uz.medion.ui.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceManager
import android.text.Spanned
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import retrofit2.HttpException
import uz.medion.R
import uz.medion.BR
import uz.medion.data.constants.Constants
import uz.medion.data.local.PrefsHelper
import uz.medion.data.retrofit.ApiClient
import java.io.IOException
import java.net.ConnectException
import java.util.*

abstract class BaseActivity<T: ViewDataBinding, V: BaseVM> : AppCompatActivity(), BaseMethods {
    private val gson: Gson = Gson()
    lateinit var prefs: PrefsHelper

    companion object{
        const val RC_UPDATE = 1
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun onBound()

    protected abstract val vm: V

    protected lateinit var binding: T
         private set

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        updateBaseContextLocale(this)

        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        doDataBinding()
    }

    override fun doDataBinding(){
        binding.lifecycleOwner = this
        binding.setVariable(1, vm)
        onBound()
    }

    override fun setStatusBarColor(color: Int) {
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            // finally change the color
            window.statusBarColor = ContextCompat.getColor(this, color)
        }
    }

    override fun showDialogNoNetwork() {
        showBasicDialog(getString(R.string.msg_no_network))
    }

    override fun showDialog(message: String, title: String?) {
        showBasicDialog(message, title)
    }

    override fun showDialog(message: Int, title: String?) {
        showBasicDialog(getString(message), title)
    }

    override fun showDialog(message: Spanned?, title: String?) {
        showBasicDialog(message, title)
    }

    override fun showDialogErrorUnknown() {
        showBasicDialog(getString(R.string.msg_unknown_error))
    }

    private fun updateBaseContextLocale(context: Context): Context{
        val locale = Locale(prefs.language ?: Constants.LANGUAGE_UZBEK)
        Locale.setDefault(locale)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return updateRecourcesLocale(context, locale)
        }
        return updateRecourcesLocaleLegacy(context, locale)
    }

    override fun showBasicDialog(message: String, title: String?) {
        if (isFinishing.not()) {
            MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(R.string.action_ok) { dialog, which ->

                }
                .show()
        }
    }

    override fun showBasicDialog(message: Spanned?, title: String?) {
        if (isFinishing.not()) {
            MaterialAlertDialogBuilder(this, R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(R.string.action_ok) { dialog, which ->

                }
                .show()
        }
    }

    override fun hideKeyboard() {
        // Check if no view has focus:
        this.currentFocus?.let {
            it.clearFocus()
            val imm: InputMethodManager? =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    override fun showKeyboard(view: View) {
        val imm: InputMethodManager? = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, 0)
    }

    override fun onRefreshTokenFail() {
        //TODO change later.
//        openActivity(AuthActivity::class) {
//            prefs.resetUserData()
//            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//        }
//        finish()
    }

    override fun handleError(it: Throwable) {
        when (it) {
            //If there is connection issue
            is ConnectException -> {
                //Check if there is internet connection at all
                InternetChecker { hasInternet ->
                    //If there is internet, that means we are not able to connect ot the server
                    if (hasInternet!!) {
                        showBasicDialog(getString(R.string.msg_cannot_connect_to_server))
                    } else {
                        //Else, there is not a internet connection
                        showDialogNoNetwork()
                    }
                }
            }
            is IOException -> showDialogNoNetwork()
            is HttpException -> {
                //TODO HTTP EXCEPTION HERE
            }
            else -> showDialogErrorUnknown()
        }
    }

    override fun handleError(it: BaseRes) {
        //TODO implement later
    }

    /**
     * Sets up a [Toolbar]. It will go beyond the window layout limits. This creates an affect of background cover the window area
     * up until [StatusBar]
     *
     * @param toolbarContainer container which holds the [Toolbar]
     * @param titleId id of string resource that will be used as a title for the [Toolbar]
     */
    override fun setToolbarLimitless(toolbarContainer: View, titleId: Int) {
        setToolbarLimitless(toolbarContainer, getString(titleId))
    }

    /**
     * Sets up a [Toolbar]. It will go beyond the window layout limits. This creates an affect of background cover the window area
     * up until [StatusBar]
     *
     * @param toolbarContainer container which holds the [Toolbar]
     * @param title title of the [Toolbar]
     */
    override fun setToolbarLimitless(toolbarContainer: View, title: String?) {
        //TODO implement later
//        val toolbar = toolbarContainer.findViewById<Toolbar>(R.id.toolbar)
//        val tvToolbarTitle = toolbar.findViewById<TextView>(R.id.tvToolbarTitle)
//
//        setSupportActionBar(toolbar)
//        tvToolbarTitle.text = title
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS )
    }

    /**
     * Changes locale of the app.
     *
     * @param language language user wishes to change to.
     */
    @SuppressWarnings("deprecation")
    override fun setLanguage(language: String) {
        ApiClient.restartRetrofit()
        Constants.language = language
        prefs.language = language
        val dm = resources.displayMetrics
        val conf = resources.configuration
        val locale = Locale(language)
        conf.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            applicationContext.createConfigurationContext(conf) //for Android 7+
        } else {
            resources.updateConfiguration(conf, dm) //for Android 6-
        }
    }

    //#region Changing locale should not be lost when user closes the app. When user restarts the app,
    //app needs to load resources in the locale that was set by user last time.
    override fun attachBaseContext(context: Context) {
        prefs =
            PrefsHelper(gson, PreferenceManager.getDefaultSharedPreferences(context))
        super.attachBaseContext(updateBaseContextLocale(context))
    }

    private fun updateRecourcesLocale(context: Context, locale: Locale): Context{
        val conf = context.resources.configuration
        conf.setLocale(locale)
        return context.createConfigurationContext(conf)
    }

    private fun updateRecourcesLocaleLegacy(context: Context, locale: Locale): Context{
        val resources = context.resources
        val conf = resources.configuration
        conf.setLocale(locale)
        resources.updateConfiguration(conf, resources.displayMetrics)
        return  context
    }
}