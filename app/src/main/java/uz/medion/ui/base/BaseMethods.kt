package uz.medion.ui.base

import android.text.Spanned
import android.view.View
import androidx.annotation.StringRes
import uz.medion.R

interface BaseMethods {

    fun setStatusBarColor(color: Int = R.attr.colorPrimary)

    fun showDialogNoNetwork()

    fun showDialog(message: String, title: String? = null)

    fun showDialog(message: Int, title: String? = null)

    fun showDialog(message: Spanned?, title: String? = null)

    fun showDialogErrorUnknown()

    fun onRefreshTokenFail()

    fun showBasicDialog(message: String, title: String? = null)

    fun showBasicDialog(message: Spanned?, title: String? = null)

    fun doDataBinding()

    fun hideKeyboard()

    fun showKeyboard(view: View)

    fun handleError(it: Throwable)

    fun handleError(it: BaseRes)

    fun setToolbarLimitless(toolbarContainer: View, @StringRes titleId: Int)

    fun setToolbarLimitless(toolbarContainer: View, title: String?)

    fun setLanguage(language: String)

    fun setStatusBarBackgroundHeight(statusBarBackground: View)
}