package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class AboutClinic(

	@field:SerializedName("urls")
	val urls: List<String>,

	@field:SerializedName("context")
	val context: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)
