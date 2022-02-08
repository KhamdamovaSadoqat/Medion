package uz.medion.utils

import android.media.MediaPlayer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

object Utils {

    private var player: MediaPlayer? = null
    private var timer: Timer? = null

    /**
     * Takes number and formats it to 3-digit separated format.
     * E.g. 100000000.4 -> 100 000 000.4
     *      100000000.0 -> 100 000 000
     *      7980 -> 7,980
     *
     * @param number number which needs to be formatted
     * @param dropDecimal determines if decimal part of the number needs to be dropped
     * @param dropComma determines if comma (to separate thousands) needs to be dropped
     */
    fun separateBy3Digits(
        number: Double?,
        dropDecimal: Boolean = false,
        dropComma: Boolean = false
    ): String {
        var formattedNumber = ""
        formattedNumber = if (dropDecimal)
            NumberFormat.getInstance().format(number?.toInt())
        else
            NumberFormat.getInstance().format(number)

        //NumberFormat formats the input with "," separation. So, replacing them if preserveComma is set to false.
        if (!dropComma)
            formattedNumber = formattedNumber.replace(",", " ")
        return formattedNumber
    }

    /**
     * Takes decimal number and formats it to get only two digits after decimal.
     * E.g. 1934.2908402 -> 1934.29
     *      12.4909090 -> 12.49
     *      13.0000 -> 13
     *
     * @param number number which needs to be formatted
     */

    fun show2DigitsAfterDecimal(number: Double): String {
        return DecimalFormat("##.##").format(number)
    }

    /**
     * Takes decimal number and formats it to get only digit after decimal.
     * E.g. 1934.2908402 -> 1934.2
     *      12.4909090 -> 12.4
     *      13.0000 -> 13
     *
     * @param number number which needs to be formatted
     */

    fun show1DigitsAfterDecimal(number: Double): String {
        return DecimalFormat("##.#").format(number)
    }






    interface AutoUpdatableAdapter {
        fun <T> RecyclerView.Adapter<*>.autoNotify(
            oldList: List<T>,
            newList: List<T>,
            compare: (T, T) -> Boolean,
        ) {
            val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return compare(oldList[oldItemPosition], newList[newItemPosition])
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int,
                ): Boolean {
                    return oldList[oldItemPosition] == newList[newItemPosition]
                }

                override fun getOldListSize() = oldList.size
                override fun getNewListSize() = newList.size
            })
            diff.dispatchUpdatesTo(this)
        }
    }
}