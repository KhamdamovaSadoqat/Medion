package uz.medion.data.model.doctor

data class TimeShiftEntityX(
    val createdAt: String,
    val endTime: EndTimeX,
    val entityStatus: String,
    val id: Int,
    val startTime: StartTimeX,
    val updatedAt: String
)