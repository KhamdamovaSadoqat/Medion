package uz.medion.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DoctorsItem(
    @DrawableRes
    val image:Int,

    @StringRes
    val name:Int,

    @StringRes
    val job:Int
)