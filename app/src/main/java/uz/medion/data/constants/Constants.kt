package uz.medion.data.constants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.R
import uz.medion.data.local.PrefsHelper
import uz.medion.data.model.*
import uz.medion.data.model.doctor.MyPatientsItem

object Constants {

    lateinit var prefs: PrefsHelper

    const val BASE_API_URL =
        "http://45.137.148.124:8081" // http://45.137.148.124:8081/api/v1/about/info
    const val GOOGLE_OAUTH =
        "863067682476-vdh3ngnrj69u96d8j2i96c2958sf4u6j.apps.googleusercontent.com"
    const val YOUTUBE_API = "AIzaSyALAoQ1eIP7lM2STLmeRpKiDaSXqv84Ulc"

    var token = ""
    var doctorId = -1
    var language = ""
    var cardNumber = "UZCARD"
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

    const val SELECT_IMAGE = 111

    const val VIEW_TYPE_SENT = 1
    const val VIEW_TYPE_RECEIVED = 2

    const val MIN_HOURS_TO_SHOW_NEW_UPDATE_PROMPT: Int = 8

    const val ADMIN_PHONE_NUMBER = "+998936285220"

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

    fun getAboutDoctorItems(): ArrayList<AboutDoctorItems> {
        return arrayListOf<AboutDoctorItems>(
            AboutDoctorItems(R.string.about_doctor, R.color.nile_blue_900, R.color.white),
            AboutDoctorItems(R.string.work, R.color.solitude_50, R.color.tangaroa_900),
            AboutDoctorItems(R.string.reyting, R.color.solitude_50, R.color.tangaroa_900),
            AboutDoctorItems(R.string.sertificate, R.color.solitude_50, R.color.tangaroa_900)
        )
    }

    fun getGender(): ArrayList<Int> {
        return arrayListOf(
            R.string.male,
            R.string.female
        )
    }

    fun getMyDoctorsDocuments(): ArrayList<MyDoctorDocumentItem> {
        return arrayListOf(
            MyDoctorDocumentItem("22 oct, 2021", "Analyzes", "Clinical results blood test", "word"),
            MyDoctorDocumentItem(
                "53 sept, 2021",
                "Reformence",
                "Clinical results blood test",
                "exel"
            ),
            MyDoctorDocumentItem(
                "12 sept, 2021",
                "Appointment",
                "Clinical results blood test",
                "pdf"
            ),
            MyDoctorDocumentItem("35 avg, 2021", "Recipe", "Clinical results blood test", "jpeg"),
            MyDoctorDocumentItem("02 avg, 2021", "EHC", "Clinical results blood test", "word"),
        )
    }

    fun getMyDoctorDocumentsType(): ArrayList<AboutDoctorItems> {
        return arrayListOf(
            AboutDoctorItems(R.string.analyzes, R.color.nile_blue_900, R.color.white),
            AboutDoctorItems(R.string.reference, R.color.solitude_50, R.color.tangaroa_900),
            AboutDoctorItems(R.string.appointment, R.color.solitude_50, R.color.tangaroa_900),
            AboutDoctorItems(R.string.recipe, R.color.solitude_50, R.color.tangaroa_900),
            AboutDoctorItems(R.string.ehc, R.color.solitude_50, R.color.tangaroa_900)
        )
    }

    fun getEsteticMedicine_categories(): ArrayList<AboutDoctorItems> {
        return arrayListOf<AboutDoctorItems>(
            AboutDoctorItems(R.string.estetic_medicine_5, R.color.nile_blue_900, R.color.white),
            AboutDoctorItems(
                R.string.estetic_medicine_6,
                R.color.solitude_50,
                R.color.tangaroa_900
            ),
            AboutDoctorItems(R.string.estetic_medicine_7, R.color.solitude_50, R.color.tangaroa_900)
        )
    }

    fun getSpaHeaders(): ArrayList<Int> {
        return arrayListOf(
            R.string.massage,
            R.string.medicine_in_bath,
            R.string.preventive_service,
            R.string.lipolytics,
            R.string.mesotherapy,
            R.string.plasmolifting,
            R.string.botulinum_toxins,
            R.string.biorevitalization,
            R.string.contour_plastic,
            R.string.healing_procedures,
            R.string.body_peels
        )
    }

    fun getSpaMassageDetails(): ArrayList<Int> {
        return arrayListOf(
            R.string.massage1,
            R.string.massage2,
            R.string.massage1,
            R.string.massage4,
            R.string.massage4,
            R.string.massage6,
            R.string.massage6,
            R.string.massage8,
            R.string.massage9,
            R.string.massage10,
            R.string.massage11,
            R.string.massage12,
            R.string.massage13,
            R.string.massage14,
            R.string.massage15,
            R.string.massage16,
            R.string.massage17,
            R.string.massage18,
            R.string.massage19,
            R.string.massage20,
            R.string.massage21,
            R.string.massage22,
            R.string.massage23,
            R.string.massage24
        )
    }

    fun getSpaMassageTimeDetails(): ArrayList<Int> {
        return arrayListOf(
            R.string.massage1_1,
            R.string.massage1_1,
            R.string.massage1_2,
            R.string.massage1_3,
            R.string.massage1_2,
            R.string.massage1_2,
            R.string.massage1_4,
            R.string.massage1_5,
            R.string.massage1_6,
            R.string.massage1_6,
            R.string.massage1_5,
            R.string.massage1_7,
            R.string.massage1_6,
            R.string.massage1_8,
            R.string.massage1_8,
            R.string.massage1_5,
            R.string.massage1_5,
            R.string.massage1_8,
            R.string.massage1_8,
            R.string.massage1_6,
            R.string.massage1_6,
            R.string.massage1_6,
            R.string.massage1_6,
            R.string.massage1_6
        )
    }

    fun getMyAccount(): ArrayList<DoctorCategoryItem> {
        return arrayListOf(
            DoctorCategoryItem(R.string.change_mobile_phone_number),
            DoctorCategoryItem(R.string.change_password),
            DoctorCategoryItem(R.string.personal_information),
            DoctorCategoryItem(R.string.my_documents),
            DoctorCategoryItem(R.string.my_doctors),
            DoctorCategoryItem(R.string.languages)
        )
    }

    fun getMyMain(): ArrayList<DoctorCategoryItem> {
        return arrayListOf(
            DoctorCategoryItem(R.string.our_doctors),
            DoctorCategoryItem(R.string.estethic_medicine),
            DoctorCategoryItem(R.string.spa_medicine),
            DoctorCategoryItem(R.string.adress_contact),
            DoctorCategoryItem(R.string.my_account)
        )
    }

    fun getMyDoctorsItem(): ArrayList<DoctorsItem> {
        return arrayListOf(
            DoctorsItem(R.drawable.doc_sevara, R.string.dc_sevara, R.string.vrach_uzi),
            DoctorsItem(R.drawable.doc_pic, R.string.dc_botir, R.string.detskiy_ortoped),
            DoctorsItem(R.drawable.doctor_img, R.string.dc_alisher, R.string.traumatology),
            DoctorsItem(R.drawable.doc_sevara, R.string.dc_elena, R.string.pediatr)
        )
    }

    fun getMyPatients(): ArrayList<MyPatientsItem> {
        return arrayListOf(
            MyPatientsItem(R.string.ilyasov_Doniyor, R.string.date, true),
            MyPatientsItem(R.string.ilyasov_Doniyor, R.string.date, true),
            MyPatientsItem(R.string.ilyasov_Doniyor, R.string.date, false),
            MyPatientsItem(R.string.ilyasov_Doniyor, R.string.date, true),
            MyPatientsItem(R.string.ilyasov_Doniyor, R.string.date, true),
            MyPatientsItem(R.string.ilyasov_Doniyor, R.string.date, false),
            MyPatientsItem(R.string.ilyasov_Doniyor, R.string.date, false),
            MyPatientsItem(R.string.ilyasov_Doniyor, R.string.date, true)
        )
    }
}