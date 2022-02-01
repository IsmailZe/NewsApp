package com.test.prismamediatesttechnique.domain

import com.test.prismamediatesttechnique.data.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher) {
    suspend fun getNews() = withContext(dispatcher) {
        newsRepository.getNews()
    }
}