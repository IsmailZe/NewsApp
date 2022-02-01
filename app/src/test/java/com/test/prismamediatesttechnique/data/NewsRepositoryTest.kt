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
class NewsRepositoryTest {

    @RelaxedMockK
    lateinit var newsServiceMock: NewsService

    @InjectMockKs
    lateinit var newsRepository: NewsRepository

    @Before
    fun setUp(){
        MockKAnnotations.init(this , relaxed = true)
    }

    @Test
    fun should_call_the_service_func_and_return_right_result() = runBlockingTest{
        coEvery { newsServiceMock.getNews().data.items } returns getNews()

        val result = newsRepository.getNews().data.items

        coVerify { newsServiceMock.getNews() }
        Truth.assertThat(result).isEqualTo(getNews())
    }

    private fun getNews() = listOf(
            News(
                body= "body",
                bodySize =  1.0,
                dateIndexed = "2021-09-06T07:00:00.000Z",
                title = "Fake News Test",
                universalUniqueIdentifier = "fake-id",
                published = "2021-09-06T07:00:00.000Z"
            )
        )


}