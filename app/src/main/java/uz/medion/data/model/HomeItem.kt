package uz.medion.data.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class HomeItem(
    @DrawableRes
    val icon:Int,
    @StringRes
    val text:Int
)