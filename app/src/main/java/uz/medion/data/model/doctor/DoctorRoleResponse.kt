package uz.medion.data.model.doctor

class DoctorRoleResponse : ArrayList<DoctorRoleResponseItem>()

data class DoctorRoleResponseItem(
    val aboutDoctor: String,
    val averageRating: String,
    val birthDate: String,
    val commentCount: Int,
    val doctorVideoUrl: String,
    val educationInfoList: List<EducationInfoXX>,
    val fullName: String,
    val id: Int,
    val image: String,
    val phoneNumber: String,
    val username: String,
    val workExperience: String,
    val workInfoList: List<WorkInfoXXXX>
)