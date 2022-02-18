package uz.medion.data.model.doctor

data class DoctorGetResponse(
    val aboutDoctor: String,
    val averageRating: String,
    val birthDate: String,
    val commentCount: Int,
    val doctorVideoUrl: String,
    val educationInfoList: List<EducationInfoXXX>,
    val fullName: String,
    val id: Int,
    val image: String,
    val phoneNumber: String,
    val username: String,
    val workExperience: String,
    val workInfoList: List<WorkInfoXXXXX>
)