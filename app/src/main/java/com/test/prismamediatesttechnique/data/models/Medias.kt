package com.test.prismamediatesttechnique.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medias(
    val imageCount: Int,
    val videoCount: Int,
) : Parcelable