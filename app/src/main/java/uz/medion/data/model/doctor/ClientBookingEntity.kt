package uz.medion.data.model.doctor

data class ClientBookingEntity(
    val bookDay: String,
    val createdAt: String,
    val entityStatus: String,
    val id: Int,
    val status: String,
    val timeShiftEntity: TimeShiftEntity,
    val updatedAt: String
)