package uz.medion.data.model.doctor

data class TimeShiftEntity(
    val createdAt: String,
    val endTime: EndTime,
    val entityStatus: String,
    val id: Int,
    val startTime: StartTime,
    val updatedAt: String
)