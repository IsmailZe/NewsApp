package com.test.prismamediatesttechnique.data

import javax.inject.Inject

class NewsService @Inject constructor(private val newsApi: NewsApi){

    suspend fun getNews() = newsApi.getNews()
}