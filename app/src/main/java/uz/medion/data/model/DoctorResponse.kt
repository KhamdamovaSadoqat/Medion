package uz.medion.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DoctorResponse(

	@field:SerializedName("doctorVideoUrl")
	val doctorVideoUrl: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("workInfoList")
	val workInfoList: List<WorkInfoListItem?>? = null,

	@field:SerializedName("workExperience")
	val workExperience: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("birthDate")
	val birthDate: String? = null,

	@field:SerializedName("aboutDoctor")
	val aboutDoctor: String? = null,

	@field:SerializedName("commentCount")
	val commentCount: Int? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("averageRating")
	val averageRating: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("educationInfoList")
	val educationInfoList: List<EducationInfoListItem?>? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class WorkInfoListItem(

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("organization")
	val organization: String? = null,

	@field:SerializedName("position")
	val position: String? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(endDate)
		parcel.writeString(organization)
		parcel.writeString(position)
		parcel.writeString(startDate)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<WorkInfoListItem> {
		override fun createFromParcel(parcel: Parcel): WorkInfoListItem {
			return WorkInfoListItem(parcel)
		}

		override fun newArray(size: Int): Array<WorkInfoListItem?> {
			return arrayOfNulls(size)
		}
	}
}

data class EducationInfoListItem(

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("organization")
	val organization: String? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null,

	@field:SerializedName("faculty")
	val faculty: String? = null
)
