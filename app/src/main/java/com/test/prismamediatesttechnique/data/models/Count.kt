package com.test.prismamediatesttechnique.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Count(
    val count: Int,
    val date: String
):Parcelable