package uz.medion.data.model.doctor



data class DoctorInfo(
    var birthday:String,
    var eduInfo:List<EduInfo>,
    var fullName:String,
    var gender:String,
    var image:String,
    var password:String,
    var phoneNumber:String,
    var roles:List<String>,
    var username:String,
    var workInfo:List<WorkInfo>

)
data class EduInfo(
    var endDate:String,
    var faculty:String,
    var organization:String,
    var startDate:String
)
data class WorkInfo(
    var endDate:String,
    var organization:String,
    var position:String,
    var startDate:String
)






