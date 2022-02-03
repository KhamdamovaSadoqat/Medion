package uz.medion.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    /**
     * Formats date which is Date format to String format
     *
     * @param date date which needs to be converted to String
     * @param format format which needs to be used
     *
     * @return String?
     */
    fun dateToText(date: Date?, format: String): String? {
        return if (date != null) {
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            sdf.format(date)
        } else
            null

    }

    /**
     * Formats the String formatted date to Date format
     *
     * @param dateText date in the String format
     * @param format format which needs to be used
     *
     * @return Date?
     */
    fun textToDate(dateText: String?, format: String): Date? {
        return if (dateText != null) {
            val sdf = SimpleDateFormat(format, Locale.getDefault())
            try {
                sdf.parse(dateText)
            } catch (e: ParseException) {
                null
            }
        } else
            null
    }

    /**
     * Formats the String formatted date to String format
     *
     * @param dateText date in the String format
     * date array of [hh:mm:ss]
     *
     * @return String
     */
    fun textToTextDate(dateText: String): String{
        val date = dateText.split(":")
        return "${date[0]}:${date[1]}"
    }
}