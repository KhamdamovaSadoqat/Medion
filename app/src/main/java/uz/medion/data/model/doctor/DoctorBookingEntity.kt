package uz.medion.data.model.doctor

data class DoctorBookingEntity(
    val bookDay: String,
    val createdAt: String,
    val entityStatus: String,
    val id: Int,
    val status: String,
    val timeShiftEntity: TimeShiftEntityX,
    val updatedAt: String
)