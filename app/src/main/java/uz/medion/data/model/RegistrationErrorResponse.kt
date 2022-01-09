package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationErrorResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("meta")
	val meta: Any? = null,

	@field:SerializedName("message")
	val message: String? = null
)
