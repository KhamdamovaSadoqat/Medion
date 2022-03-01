package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class EsteticMedicineResponse(

	@field:SerializedName("childResponses")
	val childResponses: List<ChildResponsesItem>,

	@field:SerializedName("subTitle")
	val subTitle: String,

	@field:SerializedName("mainTitle")
	val mainTitle: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("text")
	val text: String
)

data class ImageResponsesItem(

	@field:SerializedName("isMain")
	val isMain: Boolean,

	@field:SerializedName("url")
	val url: String
)

data class ChildResponsesItem(

	@field:SerializedName("subTitle")
	val subTitle: String,

	@field:SerializedName("mainTitle")
	val mainTitle: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("text")
	val text: String,

	@field:SerializedName("imageResponses")
	val imageResponses: List<ImageResponsesItem>
)
