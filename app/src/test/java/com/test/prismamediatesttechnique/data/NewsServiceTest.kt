package com.test.prismamediatesttechnique.data

import com.google.common.truth.Truth
import com.test.prismamediatesttechnique.data.models.News
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class NewsServiceTest {

    @RelaxedMockK
    lateinit var NewsApiMock: NewsApi

    @InjectMockKs
    lateinit var newsService: NewsService

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @Test
    fun should_call_the_endpoint_func_and_return_right_result() {
        val news = listOf(
            News(
                body = "body",
                bodySize = 1.0,
                dateIndexed = "2021-09-06T07:00:00.000Z",
                title = "Fake News Test",
                universalUniqueIdentifier = "fake-id",
                published = "2021-09-06T07:00:00.000Z"
            )
        )
        runBlockingTest {
            coEvery { NewsApiMock.getNews().data.items } returns news

            val result = newsService.getNews().data.items

            coVerify { NewsApiMock.getNews().data.items }
            Truth.assertThat(result).isEqualTo(result)
        }
    }
}