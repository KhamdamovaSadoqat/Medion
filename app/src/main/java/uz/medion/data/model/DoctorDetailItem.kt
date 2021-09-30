package uz.medion.data.model

import androidx.annotation.StringRes

data class DoctorDetailItem(
    @StringRes
    val doctorName: Int,
    @StringRes
    val doctorCategory: Int,
    val experience: Int,
    val comment: Int,
    @StringRes
    val clinicName: Int,
    val details: Int
)