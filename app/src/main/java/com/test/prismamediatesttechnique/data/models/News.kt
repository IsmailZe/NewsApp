package com.test.prismamediatesttechnique.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val body: String,
    val bodySize: Double,
    val category: Category? = null,
    val contentTypes: List<String>? = null,
    val dateIndexed: String,
    val keywords: List<String>? = null,
    val lead: String? = null,
    val medias: Medias? = null,
    val mostShared: Count? = null,
    val mostViewed: Count? = null,
    val published: String,
    val resource: Resource? = null,
    val title: String,
    val universalUniqueIdentifier: String,
    var favourite : Boolean = false,
):Parcelable