package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class AboutClinicResponse(

	@field:SerializedName("urls")
	val urls: List<String>,

	@field:SerializedName("context")
	val context: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
