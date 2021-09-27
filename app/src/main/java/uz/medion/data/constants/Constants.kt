package uz.medion.data.constants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.R
import uz.medion.data.local.PrefsHelper
import uz.medion.data.model.DoctorCategoryItem
import uz.medion.data.model.DoctorDetailItem
import uz.medion.data.model.HomeItem

object Constants {

    lateinit var prefs: PrefsHelper

    const val BASE_API_URL = "HERE SHOULD BE THAT URL WHICH WE SHOULD GET"

    var token = ""
    var language = ""
    private val unAuthorized = MutableLiveData<Boolean>()

    fun setUnAuthorized(isUnAuthorized: Boolean) {
        unAuthorized.value = isUnAuthorized
    }

    fun getUnAuthorized(): LiveData<Boolean> {
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

    //    <string name="lor">lor</string>
//    <string name="ginecology">ginecology</string>
//    <string name="pediatr">pediatr</string>
//    <string name="neuralogy">neurology</string>
//    <string name="traumatology">traumatology</string>
//    <string name="cardiology">cardiology</string>
//    <string name="eko">eko</string>
//    <string name="cardio_operation">cardio operation</string>
//    <string name="urology">urology</string>
//    <string name="endocrinology">endocrinology</string>
//    <string name="allergiology">allergiology</string>
//    <string name="mammology">mammology</string>
//    <string name="dermatology">dermatology</string>
//    <string name="psychology">psychology</string>
//    <string name="pulmonology">pulmonology</string>
//    <string name="psychoterapy">psychoterapy</string>
//    <string name="medicining">medicining</string>
//    <string name="electro_encefalography">electro encefalography</string>
//    <string name="gastroenterology">gastroenterology</string>
//    <string name="uzi">uzi</string>
//    <string name="uzi_maternity">uzi maternity</string>
//    <string name="mrt">mrt</string>
//    <string name="mskt">mskt</string>
//    <string name="endoscopy">endoscopy</string>
//
    fun getHomeItems(): ArrayList<HomeItem> {
        return arrayListOf(
            HomeItem(R.drawable.ic_lor, R.string.lor),
            HomeItem(R.drawable.ic_ginecology, R.string.ginecology),
            HomeItem(R.drawable.ic_pediatr, R.string.pediatr),
            HomeItem(R.drawable.ic_neurolog, R.string.neuralogy),
            HomeItem(R.drawable.ic_traumatolog, R.string.traumatology),
            HomeItem(R.drawable.ic_cardiolog, R.string.cardiology),
            HomeItem(R.drawable.ic_eko, R.string.eko),
            HomeItem(R.drawable.ic_cardio_operation, R.string.cardio_operation),
            HomeItem(R.drawable.ic_urolog, R.string.urology),
            HomeItem(R.drawable.ic_endocrinolog, R.string.endocrinology),
            HomeItem(R.drawable.ic_allergolog, R.string.allergiology),
            HomeItem(R.drawable.ic_mammolog, R.string.mammology),
            HomeItem(R.drawable.ic_dermatolog, R.string.dermatology),
            HomeItem(R.drawable.ic_psycology, R.string.psychology),
            HomeItem(R.drawable.ic_pulmonolog, R.string.pulmonology),
            HomeItem(R.drawable.ic_psychoterapy, R.string.psychoterapy),
            HomeItem(R.drawable.ic_medicining, R.string.medicining),
            HomeItem(R.drawable.ic_electro_smth, R.string.electro_encefalography),
            HomeItem(R.drawable.ic_gastroentesy, R.string.gastroenterology),
            HomeItem(R.drawable.ic_uzi, R.string.uzi),
            HomeItem(R.drawable.ic_uzi_maternity, R.string.uzi_maternity),
            HomeItem(R.drawable.ic_mrt, R.string.mrt),
            HomeItem(R.drawable.ic_mckt, R.string.mskt),
            HomeItem(R.drawable.ic_endoscopy, R.string.endoscopy)
        )
    }

    fun getOurDoctorCategory(): ArrayList<DoctorCategoryItem> {
        return arrayListOf(
            DoctorCategoryItem(R.string.lor),
            DoctorCategoryItem(R.string.ginecology),
            DoctorCategoryItem(R.string.pediatr),
            DoctorCategoryItem(R.string.neuralogy),
            DoctorCategoryItem(R.string.traumatology),
            DoctorCategoryItem(R.string.cardiology),
            DoctorCategoryItem(R.string.eko),
            DoctorCategoryItem(R.string.cardio_operation),
            DoctorCategoryItem(R.string.urology),
            DoctorCategoryItem(R.string.endocrinology),
            DoctorCategoryItem(R.string.allergiology),
            DoctorCategoryItem(R.string.mammology),
            DoctorCategoryItem(R.string.dermatology),
            DoctorCategoryItem(R.string.psychology),
            DoctorCategoryItem(R.string.pulmonology),
            DoctorCategoryItem(R.string.psychoterapy),
            DoctorCategoryItem(R.string.medicining),
            DoctorCategoryItem(R.string.electro_encefalography),
            DoctorCategoryItem(R.string.gastroenterology),
            DoctorCategoryItem(R.string.uzi),
            DoctorCategoryItem(R.string.uzi_maternity),
            DoctorCategoryItem(R.string.mrt),
            DoctorCategoryItem(R.string.mskt),
            DoctorCategoryItem(R.string.endoscopy)
        )
    }

    fun getOurDoctorDetail(): ArrayList<DoctorDetailItem> {
        return arrayListOf(
            DoctorDetailItem(
                R.string.ilyasov_Doniyor,
                R.string.main_doctor_of_urology,
                22,
                64,
                R.string.clinic_name,
                R.string.doc_detail
            ),
            DoctorDetailItem(
                R.string.nuridinov_hojiakbar,
                R.string.main_doctor_of_urology,
                22,
                64,
                R.string.clinic_name,
                R.string.doc_detail
            ),
            DoctorDetailItem(
                R.string.ilyasov_Doniyor,
                R.string.main_doctor_of_urology,
                22,
                64,
                R.string.clinic_name,
                R.string.doc_detail
            ),
            DoctorDetailItem(
                R.string.nuridinov_hojiakbar,
                R.string.main_doctor_of_urology,
                22,
                64,
                R.string.clinic_name,
                R.string.doc_detail
            )
        )
    }
}