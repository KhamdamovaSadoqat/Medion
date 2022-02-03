package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class MonthlyTimeResponse(

	@field:SerializedName("localTime")
	val localTime: String? = null,

	@field:SerializedName("isActiv")
	val isActive: Boolean? = null
)
