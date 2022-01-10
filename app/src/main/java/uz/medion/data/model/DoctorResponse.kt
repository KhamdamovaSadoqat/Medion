package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class DoctorResponse(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String,

	@field:SerializedName("workInfoList")
	val workInfoList: List<WorkInfoListItem>,

	@field:SerializedName("fullName")
	val fullName: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("educationInfoList")
	val educationInfoList: List<EducationInfoListItem>,

	@field:SerializedName("birthDate")
	val birthDate: String,

	@field:SerializedName("username")
	val username: String
)

data class EducationInfoListItem(

	@field:SerializedName("endDate")
	val endDate: String,

	@field:SerializedName("organization")
	val organization: String,

	@field:SerializedName("startDate")
	val startDate: String,

	@field:SerializedName("faculty")
	val faculty: String
)

data class WorkInfoListItem(

	@field:SerializedName("endDate")
	val endDate: Any,

	@field:SerializedName("organization")
	val organization: String,

	@field:SerializedName("position")
	val position: String,

	@field:SerializedName("startDate")
	val startDate: String
)
