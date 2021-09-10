package uz.medion.utils

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.annotation.RawRes
import com.google.gson.Gson
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
        val formattedNumber = DecimalFormat("##.##").format(number)
        return formattedNumber
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
        val formattedNumber = DecimalFormat("##.#").format(number)
        return formattedNumber
    }


    fun htmlToText(string: String): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(string)
        }
    }

    fun changeHtmltoString(html: String): String {
        return Html.fromHtml(html).toString()
    }

    fun toJson(data: Any): String {
        val gson = Gson()
        return gson.toJson(data)
    }

    inline fun <reified T> fromJson(data: String): T {
        val gson = Gson()
        return gson.fromJson(data, T::class.java)
    }

    fun getTime(min: Int, sec: Int): String {
        var timeFormat = ""
        timeFormat = if (min < 10) {
            if (sec < 10) {
                "0$min:0$sec"
            } else {
                "0$min:$sec"
            }
        } else {
            if (sec < 10) {
                "$min:0$sec"
            } else {
                "$min:$sec"
            }
        }
        return timeFormat
    }

    fun playSound(
        context: Context,
        @RawRes resourse: Int,
        playOrPause: Boolean = false,
        progress: ((Int, Int) -> Unit)? = null
    ) {
        if (playOrPause && player != null) {
            if (player!!.isPlaying) {
                player!!.pause()
                return
            } else {
                player!!.start()
                return
            }
        }
        player = MediaPlayer.create(context, resourse)
        player!!.isLooping = false
        player!!.start()

        if (progress != null) {
            timer = Timer()
            getProgress(player!!, player!!.duration / 100L, progress)
        }

    }

    fun removePLayer() {
        player?.let {
            it.stop()
            it.release()
        }
        timer?.cancel()
    }

    private fun getProgress(player: MediaPlayer, duration: Long, progress: (Int, Int) -> Unit) {
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                val dur = player.duration
                if (player.currentPosition != dur) {
                    progress.invoke(dur, player.currentPosition)
                    getProgress(player, duration, progress)
                } else {
                    progress.invoke(dur, player.currentPosition)
                }
            }
        }, 1000)
    }
}