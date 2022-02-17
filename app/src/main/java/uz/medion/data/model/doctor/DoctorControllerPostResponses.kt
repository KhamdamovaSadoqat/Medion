package uz.medion.data.model.doctor

data class DoctorControllerPostResponses(
    val aboutDoctor: String,
    val averageRating: String,
    val birthDate: String,
    val clientBookingEntities: List<ClientBookingEntity>,
    val commentCount: Int,
    val createdAt: String,
    val doctorBookingEntities: List<DoctorBookingEntity>,
    val doctorVideoUrl: String,
    val educationInfoList: List<EducationInfo>,
    val entityStatus: String,
    val fullName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val password: String,
    val phoneNumber: String,
    val roles: List<String>,
    val specialities: List<Speciality>,
    val status: String,
    val updatedAt: String,
    val username: String,
    val workExperience: String,
    val workInfoList: List<WorkInfoX>
)