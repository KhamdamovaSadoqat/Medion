package uz.medion.utils

import java.text.DateFormat
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
    fun textToTextDate(dateText: String): String {
        val date = dateText.split(":")
        return "${date[0]}:${date[1]}"
    }

    fun timeMillsToTextDate(timeMills: Long): String {
        //timeMills to dd.mm.yyyy
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeMills
        return if (calendar[Calendar.MONTH] < 10) "${calendar[Calendar.DAY_OF_MONTH]}.0${calendar[Calendar.MONTH]}.${calendar[Calendar.YEAR]}"
        else "${calendar[Calendar.DAY_OF_MONTH]}.${calendar[Calendar.MONTH]}.${calendar[Calendar.YEAR]}"
    }

    fun timeMillsToTextDate2(timeMills: Long): String{
        //timeMills to yyyy-mm-dd
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeMills
        return if (calendar[Calendar.MONTH] < 9 && calendar[Calendar.DAY_OF_MONTH] < 10) "${calendar[Calendar.YEAR]}-0${calendar[Calendar.MONTH]+1}-0${calendar[Calendar.DAY_OF_MONTH]}"
        else if(calendar[Calendar.MONTH] < 9 && calendar[Calendar.DAY_OF_MONTH] >= 10) "${calendar[Calendar.YEAR]}-0${calendar[Calendar.MONTH]+1}-${calendar[Calendar.DAY_OF_MONTH]}"
        else if(calendar[Calendar.MONTH] >= 9 && calendar[Calendar.DAY_OF_MONTH] >= 10) "${calendar[Calendar.YEAR]}-${calendar[Calendar.MONTH]+1}-${calendar[Calendar.DAY_OF_MONTH]}"
        else if(calendar[Calendar.MONTH] >= 9 && calendar[Calendar.DAY_OF_MONTH] < 10) "${calendar[Calendar.YEAR]}-${calendar[Calendar.MONTH]+1}-0${calendar[Calendar.DAY_OF_MONTH]}"
        else "${calendar[Calendar.YEAR]}-${calendar[Calendar.MONTH]+1}-${calendar[Calendar.DAY_OF_MONTH]}"
    }
}