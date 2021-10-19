package uz.medion.data.model

import androidx.annotation.DrawableRes

data class Location (
    @DrawableRes
    var pic: Int,
    var latitude: String,
    var longitude: String
)