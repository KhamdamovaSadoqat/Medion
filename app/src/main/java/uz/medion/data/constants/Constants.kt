package uz.medion.data.constants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.local.PrefsHelper

object Constants {

    lateinit var prefs: PrefsHelper

    const val BASE_API_URL = "HERE SHOULD BE THAT URL WHICH WE SHOULD GET"

    var token = ""
    var language = ""
    private val unAuthorized = MutableLiveData<Boolean>()

    fun setUnAuthorized(isUnAuthorized: Boolean){
        unAuthorized.value = isUnAuthorized
    }

    fun getUnAuthorized(): LiveData<Boolean>{
        return unAuthorized
    }

    const val HEADER_AUTHORIZATION = "Authorization"

    const val PREFIX_UZB_PHONE = "+998"
    const val REGEX_UZB_PHONE = "{+998}{ }[00]{ }[000]{ }[00]{ }[00]"
    const val REGEX_VALID_UZB_PHONE = "\\998\\s?9\\d\\s?\\d{3}\\s?\\d{2}\\s?\\d{2}"
    const val REGEX_MONEY = "[000] [000] [000] " //Maximum of 999 999 999 is allowed

    const val LENGTH_PIN: Int = 4
    const val LENGTH_PASSWORD_MIN: Int = 6

    const val DATE_FORMAT_DOB = "dd MMMM yyyy" //02 November 2020
    const val DATE_FORMAT_DOB_SERVER = "yyy-MM-dd"
    const val DATE_FORMAT_SERVER_TIME = "yyyy-MM-dd'T'HH:mm:ss.SSS" // 2020-10-20T06:54:33.879

    const val MALE: String = "M"
    const val FEMALE: String = "F"

    const val LANGUAGE_RUSSIAN: String = "ru"
    const val LANGUAGE_UZBEK: String = "uz"
    const val LANGUAGE_ENGLISH: String = "en"

    const val ENABLED: Int = 1
    const val DISABLED: Int = 0

    const val DENSITY_XXXHDPI = 4.0
    const val DENSITY_XXHDPI = 3.0
    const val DENSITY_XHDPI = 2.0
    const val DENSITY_HDPI = 1.5
    const val DENSITY_MDPI = 1.0
    const val DENSITY_LDPI = 0.75

    const val MIN_HOURS_TO_SHOW_NEW_UPDATE_PROMPT: Int = 8
}