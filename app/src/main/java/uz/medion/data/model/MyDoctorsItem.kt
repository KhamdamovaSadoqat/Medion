package uz.medion.data.model

import androidx.annotation.StringRes

data class MyDoctorsItem(
    @StringRes
    val Name: Int,
    @StringRes
    val CategoryName: Int,
    val isFavourite: Boolean
)