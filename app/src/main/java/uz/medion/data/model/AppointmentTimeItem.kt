package uz.medion.data.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AppointmentTimeItem(
    @DrawableRes
    val background: Int,
    @StringRes
    val time: Int,
    @ColorRes
    val textColor: Int,
    val clickable: Boolean,
    val focusable: Boolean
)