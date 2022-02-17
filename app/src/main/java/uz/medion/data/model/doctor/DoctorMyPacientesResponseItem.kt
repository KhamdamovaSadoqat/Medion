package uz.medion.data.model.doctor

data class DoctorMyPacientesResponseItem(
    val birthDate: String,
    val fullName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val isFavorite: Boolean,
    val phoneNumber: String,
    val username: String
)