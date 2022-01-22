package uz.medion.data.model.doctor

data class DocRegistration(
//registration-controller POST
    val  birthDate:String,
    val fullName:String,
    val gender :String,
    val image:String,
    val password:String,
    val phoneNumber:String,
    val userName:String
)

