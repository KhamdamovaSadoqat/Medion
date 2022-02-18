package uz.medion.data.model.doctor

data class DoctorPutBody(
    val birthDate: String,
    val eduInfo: List<EduInfoX>,
    val fullName: String,
    val gender: String,
    val image: String,
    val password: String,
    val phoneNumber: String,
    val username: String,
    val workInfo: List<WorkInfoXX>
)