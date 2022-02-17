package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class MonthlyDateResponse(

	@field:SerializedName("localDate")
	val localDate: String? = null,

	@field:SerializedName("open")
	val open: Boolean? = null
)
