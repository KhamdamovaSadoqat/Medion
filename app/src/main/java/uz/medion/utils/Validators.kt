package uz.medion.utils

import android.text.TextUtils
import uz.medion.data.constants.Constants

object Validators {
    fun isEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target)
            .matches()
    }

    fun isPassword(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && target.isNotEmpty()
    }

    fun isCode(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && target.length == 4
    }

    fun isValidPhoneNumber(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.PHONE.matcher(target)
            .matches() && Regex(
            Constants.REGEX_VALID_UZB_PHONE
        ).matches(target)
    }

    fun isPasswordLengthSatisfied(target: CharSequence, length: Int): Boolean {
        return target.toString().removeSpaces().length >= length
    }
}