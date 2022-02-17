package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class BookingResponse(

	@field:SerializedName("doctorName")
	val doctorName: String? = null,

	@field:SerializedName("clientName")
	val clientName: String? = null,

	@field:SerializedName("bookDay")
	val bookDay: String? = null,

	@field:SerializedName("bookTime")
	val bookTime: BookTime? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class BookTime(

	@field:SerializedName("hour")
	val hour: Int? = null,

	@field:SerializedName("nano")
	val nano: Int? = null,

	@field:SerializedName("minute")
	val minute: Int? = null,

	@field:SerializedName("second")
	val second: Int? = null
)
