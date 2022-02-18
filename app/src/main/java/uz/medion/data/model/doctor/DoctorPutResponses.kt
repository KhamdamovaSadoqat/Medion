package uz.medion.data.model.doctor

data class DoctorPutResponses(
    val aboutDoctor: String,
    val averageRating: String,
    val birthDate: String,
    val clientBookingEntities: List<ClientBookingEntityX>,
    val commentCount: Int,
    val createdAt: String,
    val doctorBookingEntities: List<DoctorBookingEntityX>,
    val doctorVideoUrl: String,
    val educationInfoList: List<EducationInfoX>,
    val entityStatus: String,
    val fullName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val password: String,
    val phoneNumber: String,
    val roles: List<String>,
    val specialities: List<SpecialityX>,
    val status: String,
    val updatedAt: String,
    val username: String,
    val workExperience: String,
    val workInfoList: List<WorkInfoXXX>
)