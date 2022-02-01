package com.test.prismamediatesttechnique.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val depth: Int,
    val descriptionSeo: String,
    val resource: Resource,
    val title: String,
    val titleSeo: String
): Parcelable