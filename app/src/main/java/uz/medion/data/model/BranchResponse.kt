package uz.medion.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BranchResponse(

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

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
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString()) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(imgUrl)
		parcel.writeString(address)
		parcel.writeString(entityStatus)
		parcel.writeString(phone)
		parcel.writeString(latitude)
		parcel.writeString(context)
		parcel.writeValue(id)
		parcel.writeString(title)
		parcel.writeString(longitude)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<BranchResponse> {
		override fun createFromParcel(parcel: Parcel): BranchResponse {
			return BranchResponse(parcel)
		}

		override fun newArray(size: Int): Array<BranchResponse?> {
			return arrayOfNulls(size)
		}
	}
}