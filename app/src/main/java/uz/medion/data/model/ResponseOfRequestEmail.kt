package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class ResponseOfRequestEmail(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("timeOut")
	val timeOut: Int? = null
)
