package com.test.prismamediatesttechnique.data

import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsService: NewsService) {

    suspend fun getNews() = newsService.getNews()
}