package uz.medion.data.model.doctor

data class DoctorsControllerPostBody(
    val birthDate: String,
    val eduInfo: List<EduInfo>,
    val fullName: String,
    val gender: String,
    val image: String,
    val password: String,
    val phoneNumber: String,
    val roles: List<String>,
    val username: String,
    val workInfo: List<WorkInfo>
)