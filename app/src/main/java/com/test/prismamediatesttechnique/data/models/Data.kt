package com.test.prismamediatesttechnique.data.models

data class Data(
    val currentItemCount: Int,
    val date: String,
    val items: List<News>,
    val itemsPerPage: Int,
    val startIndex: Int,
    val totalItems: Int
)