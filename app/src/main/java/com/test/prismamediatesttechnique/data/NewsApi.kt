package com.test.prismamediatesttechnique.data

import com.test.prismamediatesttechnique.data.models.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsApi {

    @Headers("accept: application/json")
    @GET("news.json")
    suspend fun getNews(): ResponseBody
}