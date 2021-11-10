package uz.medion.ui.main.user.appointment

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import java.lang.String

object EditTextListenerExpireDate : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        // Delete char where needed.
        if (s!!.isNotEmpty() && s.length % 3 == 0) {
            val c: Char = s[s.length - 1]
            if ('/' == c) {
                s.delete(s.length - 1, s.length)
            }
        }
        // Insert char where needed.
        if (s.isNotEmpty() && s.length % 3 == 0) {
            val c: Char = s[s.length - 1]
            // Only if its a digit where there should be a space we insert a space
            Log.d("----------", "afterTextChanged: c: $c")
            if (Character.isDigit(c)) {
                s.insert(s.length-1, String.valueOf('/'))
            }
        }
    }
}