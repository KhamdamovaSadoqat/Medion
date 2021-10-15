package uz.medion.utils

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import kotlin.reflect.KClass

fun Context.showToast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun SharedPreferences.put(key: String, value: String?){
    edit().putString(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Int){
    edit().putInt(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Long) {
    edit().putLong(key, value).apply()
}

fun SharedPreferences.put(key: String, value: Boolean) {
    edit().putBoolean(key, value).apply()
}

fun Context.openActivity(
    activityClass: KClass<out AppCompatActivity>,
    action: ((intent: Intent) -> Unit)? = null
) {
    val intent = Intent(this, activityClass.java)
    action?.invoke(intent)
    startActivity(intent)
}

fun AppCompatActivity.openActivityForResult(
    activityClass: KClass<out AppCompatActivity>,
    requestCode: Int,
    action: ((intent: Intent) -> Unit)? = null
) {
    val intent = Intent(this, activityClass.java)
    action?.invoke(intent)
    startActivityForResult(intent, requestCode)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visibilityBoolean(isVisible: Boolean?) {
    visibility = if (isVisible == true) View.VISIBLE else View.GONE
}

fun Context.getColorFromRes(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun ViewGroup.inflateView(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun EditText.addAfterChangeWatcher(action: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            action.invoke(s?.toString().orEmpty())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
fun Float.toPx(): Float = this * Resources.getSystem().displayMetrics.density

fun String.removeSpaces(): String {
    return this.trim().replace(" ", "")
}

fun String.removeNonNumberChars(): String {
    val regex = Regex("[^0-9]")
    return regex.replace(this, "")
}

fun String.formatPhone(): String {
    val phone = "+$this"
    return phone.substring(0..3) + "xx(xxx)-" + phone.substring(9..12)
}

fun MaterialButton.changeColor(color: Int) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        this.background.setTintList(ContextCompat.getColorStateList(context, color))
    } else {
        this.setBackgroundColor(ContextCompat.getColor(context, color))
    }
}

fun View.changeColor(color: Int) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        this.background.setTintList(ContextCompat.getColorStateList(context, color))
    } else {
        this.setBackgroundColor(ContextCompat.getColor(context, color))
    }
}

fun Context.themeColor(@AttrRes color: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(color, typedValue, true)
    return typedValue.data
}