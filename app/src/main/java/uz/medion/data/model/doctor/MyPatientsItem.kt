package uz.medion.data.model.doctor

import androidx.annotation.StringRes

class MyPatientsItem(
    @StringRes
    val Name:Int,
    @StringRes
    val date:Int,
    val isFavourite:Boolean
)