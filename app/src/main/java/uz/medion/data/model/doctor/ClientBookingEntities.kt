package uz.medion.data.model.doctor

class ClientBookingEntities(
    var id :Int,
    var bookDay:String,
    var createdAt:String,
    var entityStatus:String,
    var status:String,
    var timeShiftEntity:TimeShiftEntity,
    var updatedAt:String
)
data class TimeShiftEntity(
    var createdAt:String,
    var endTime: EndTime,
    var entityStatus:String,
    var id :Int = -1,
    var startTime:StartTime,
    var updatedAt:String
)
data class EndTime(
    var hour:Int,
    var minute : Int,
    var nano:Int,
    var second:Int
)
data class StartTime(
    var hour:Int,
    var minute:Int,
    var nano :Int,
    var second: Int,
)