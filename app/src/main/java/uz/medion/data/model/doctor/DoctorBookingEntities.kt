package uz.medion.data.model.doctor

data class DoctorBookingEntities(
    var bookDay: String,
    var createdAt: String,
    var entityStatus:String,
    var id:Int=-1,
    var status:String,
    var timeShiftEntity:TimeShiftEntity
    )
data class DoctorTimeShiftEntity(
    var createdAt:String
)