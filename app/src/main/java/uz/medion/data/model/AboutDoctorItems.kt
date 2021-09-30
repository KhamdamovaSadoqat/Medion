package uz.medion.data.model

import androidx.annotation.ColorInt
import androidx.annotation.StringRes

data class AboutDoctorItems(
    @StringRes
    val categoryName: Int,
    val backgroundColor: Int,
    val textColor: Int
)