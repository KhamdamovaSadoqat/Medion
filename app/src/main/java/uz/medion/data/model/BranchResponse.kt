package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class BranchResponse(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: Any? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("entityStatus")
	val entityStatus: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("context")
	val context: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: Any? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)
