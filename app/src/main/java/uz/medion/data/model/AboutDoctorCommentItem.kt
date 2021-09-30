package uz.medion.data.model

import androidx.annotation.StringRes

data class AboutDoctorCommentItem (
    @StringRes
    var comment: Int,
    var reyting: Int,
    var date: String

)