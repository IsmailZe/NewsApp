package com.test.prismamediatesttechnique.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.argThat
import com.test.prismamediatesttechnique.data.models.News
import com.test.prismamediatesttechnique.data.models.State
import com.test.prismamediatesttechnique.domain.NewsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var NewsUseCaseMock: NewsUseCase

    @MockK
    lateinit var observer: Observer<State<List<News>>>

    @InjectMockKs
    lateinit var newsViewModel: NewsViewModel

    var dispatcherMock = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        Dispatchers.setMain(dispatcherMock)
    }

    @Test
    fun check_when_success() = dispatcherMock.runBlockingTest {
        coEvery { NewsUseCaseMock.getNews().data.items } returns getNews()
        newsViewModel.newsLiveData.observeForever(observer)

        newsViewModel.getNews()

        coVerify { NewsUseCaseMock.getNews().data.items }
        coEvery { observer.onChanged(State.DataState(getNews())) }

    }


    @Test
    fun check_when_error() = dispatcherMock.runBlockingTest {
        coEvery { NewsUseCaseMock.getNews() } coAnswers {
            throw IllegalStateException("")
        }
        newsViewModel.newsLiveData.observeForever(observer)

        newsViewModel.getNews()

        coVerify { NewsUseCaseMock.getNews() }
        coEvery {
            observer.onChanged(argThat {
                this is State.ErrorState
            })
        }

    }

    private fun getNews() = listOf(
        News(
            body = "body",
            bodySize = 1.0,
            dateIndexed = "2021-09-06T07:00:00.000Z",
            title = "Fake News Test",
            universalUniqueIdentifier = "fake-id",
            published = "2021-09-06T07:00:00.000Z"
        )
    )

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcherMock.cleanupTestCoroutines()
    }
}