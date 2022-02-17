package uz.medion.data.model.doctor

data class DoctorGetFavouritesResponseItem(
    val birthDate: String,
    val fullName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val phoneNumber: String,
    val username: String
)