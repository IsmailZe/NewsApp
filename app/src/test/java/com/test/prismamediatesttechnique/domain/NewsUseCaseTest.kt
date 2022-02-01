package com.test.prismamediatesttechnique.domain

import com.test.prismamediatesttechnique.data.NewsRepository
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class NewsUseCaseTest {

    @RelaxedMockK
    lateinit var newsRepositoryMock: NewsRepository

    var dispatcherMock = TestCoroutineDispatcher()

    lateinit var newsUseCase: NewsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        Dispatchers.setMain(dispatcherMock)
        newsUseCase = NewsUseCase(newsRepositoryMock, dispatcherMock)
    }

    @Test
    fun check_thar_get_news_is_ok() {
        dispatcherMock.runBlockingTest {
            newsUseCase.getNews()

            coVerify { newsRepositoryMock.getNews() }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcherMock.cleanupTestCoroutines()
    }
}