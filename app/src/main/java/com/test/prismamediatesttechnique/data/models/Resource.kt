package com.test.prismamediatesttechnique.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Resource(
    val href: String,
    val id: String,
    val index: String,
    val indexed: String,
    val modified: String,
    val path: String,
    val published: String,
    val source: String,
    val type: String,
    val url: String
):Parcelable