package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
