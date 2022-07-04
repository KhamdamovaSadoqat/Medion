package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("bloodGroup")
	val bloodGroup: Any? = null,

	@field:SerializedName("image")
	val image: Any? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("weight")
	val weight: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("birthDate")
	val birthDate: Any? = null,

	@field:SerializedName("username")
	val username: Any? = null,

	@field:SerializedName("height")
	val height: Any? = null
)
