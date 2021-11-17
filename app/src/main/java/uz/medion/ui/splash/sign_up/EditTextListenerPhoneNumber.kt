package uz.medion.ui.splash.sign_up

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import java.lang.String

object EditTextListenerPhoneNumber : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        Log.d("----------", "beforeTextChanged: ")
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        Log.d("----------", "onTextChanged: ")
    }

    override fun afterTextChanged(s: Editable?) {
        // max length: "998 90 975 95 47" = 17

        // Delete char where needed.
        if (s!!.length == 5 ||
            s.length == 8 ||
            s.length == 12 ||
            s.length == 15
        ) {
            val c: Char = s[s.length - 1]
            if (' ' == c) {
                s.delete(s.length - 1, s.length)
            }
        }
        // Insert char where needed.
        if (s.length == 5 ||
            s.length == 8 ||
            s.length == 12 ||
            s.length == 15
        ) {
            val c: Char = s[s.length - 1]
            if (Character.isDigit(c) && TextUtils.split(
                    s.toString(),
                    String.valueOf(' ')
                ).size <= 5
            ) {
                s.insert(s.length - 1, String.valueOf(' '))
            }
        }
    }

}