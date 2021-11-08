package uz.medion.ui.main.user.payment

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentAddCardBinding
import uz.medion.ui.base.BaseFragment
import com.google.android.youtube.player.internal.s

import android.text.TextUtils
import android.util.Log
import android.view.View
import java.lang.String
import java.lang.StringBuilder


class AddCardFragment : BaseFragment<FragmentAddCardBinding, PaymentVM>(), TextWatcher {

    private val TOTAL_SYMBOLS = 19 // size of pattern 0000-0000-0000-0000

    private val TOTAL_DIGITS = 16 // max numbers of digits in pattern: 0000 x 4

    private val DIVIDER_MODULO = 5 // means divider position is every 5th symbol beginning with 1

    private val DIVIDER_POSITION =
        DIVIDER_MODULO - 1 // means divider position is every 4th symbol beginning with 0

    private val DIVIDER = '-'

    private val space = ' '

    override fun onBound() {
        binding.etCardNumber.addTextChangedListener(this)
    }

    @SuppressLint("LogConditional")
    fun setUp() {
        binding.etCardNumber.setOnFocusChangeListener { _, hasFocus ->
            Log.d("-------", "setUp: focus: $hasFocus")
            if (hasFocus) {
                Log.d("-------", "setUp: focus: $hasFocus")
                binding.etCardNumber.hint = ""
            } else {
                Log.d("-------", "setUp: focus: $hasFocus")
                binding.etCardNumber.hint = "Your hint"
            }

        }
    }

    override fun getLayoutResId() = R.layout.fragment_add_card
    override val vm: PaymentVM
        get() = ViewModelProvider(this).get(PaymentVM::class.java)

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(s: Editable?) {

        if(s!!.isNotEmpty() && s.length%5 == 0){
            s.insert(s.length-1, String.valueOf(space))
        }

//        Log.d("----------", "afterTextChanged: working")
//        if (s!!.isNotEmpty() && s.length % 5 == 0) {
//            Log.d("----------", "afterTextChanged: 111")
//            val c: Char = s[s.length - 1]
//            if (space == c) {
//                s.delete(s.length - 1, s.length)
//            }
//        }
//        // Insert char where needed.
//        if (s.isNotEmpty() && s.length % 5 == 0) {
//            Log.d("----------", "afterTextChanged: 2222")
//            val c: Char = s[s.length - 1]
//            // Only if its a digit where there should be a space we insert a space
//            if (Character.isDigit(c) && TextUtils.split(
//                    s.toString(),
//                    String.valueOf(space)
//                ).size <= 3
//            ) {
//                Log.d("----------", "afterTextChanged: 33333")
//                s.insert(s.length - 1, String.valueOf(space))
//            }
//        }


//        if (!isInputCorrect(s!!, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER)) {
//            s.replace(0, s.length, buildCorrectString(getDigitArray(s, TOTAL_DIGITS)!!, DIVIDER_POSITION, DIVIDER))
//        }

//        if (!s?.let { isInputCorrect(it, TOTAL_SYMBOLS, DIVIDER_MODULO, DIVIDER) }!!) {
//            s.replace(0, s.length,
//                getDigitArray(s, TOTAL_DIGITS)?.let {
//                    buildCorrectString(
//                        it,
//                        DIVIDER_POSITION,
//                        DIVIDER
//                    )
//                })
//        }
    }

//    private fun isInputCorrect(
//        s: Editable,
//        totalSymbols: Int,
//        dividerModulo: Int,
//        divider: Char
//    ): Boolean {
//        var isCorrect = s.length <= totalSymbols // check size of entered string
//        for (i in s.indices) { // check that every element is right
//            isCorrect = if (i > 0 && (i + 1) % dividerModulo == 0) {
//                isCorrect and (divider == s[i])
//            } else {
//                isCorrect and Character.isDigit(s[i])
//            }
//        }
//        return isCorrect
//    }
//
//    private fun buildCorrectString(
//        digits: CharArray,
//        dividerPosition: Int,
//        divider: Char
//    ): kotlin.String {
//        val formatted = StringBuilder()
//        for (i in digits.indices) {
//            if (digits[i] != 0) {
//                formatted.append(digits[i])
//                if (i > 0 && i < digits.size - 1 && (i + 1) % dividerPosition == 0) {
//                    formatted.append(divider)
//                }
//            }
//        }
//        return formatted.toString()
//    }
//
//    private fun getDigitArray(s: Editable, size: Int): CharArray {
//        val digits = CharArray(size)
//        var index = 0
//        var i = 0
//        while (i < s.length && index < size) {
//            val current = s[i]
//            if (Character.isDigit(current)) {
//                digits[index] = current
//                index++
//            }
//            i++
//        }
//        return digits
//    }

}