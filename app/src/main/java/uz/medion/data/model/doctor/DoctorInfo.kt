package uz.medion.data.model.doctor

class DoctorInfo(
    var id: String,
    var aboutDoctor: String,
    var averageRating: String,
    var birthDate: String,
    var clientBookingEntities: List<ClientBookingEntities>,
    var commentCount:Int,
    var createdAt:String,
    var doctorBookingEntities:List<DoctorBookingEntities>,

)






