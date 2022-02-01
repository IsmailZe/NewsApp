package com.test.prismamediatesttechnique.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.prismamediatesttechnique.data.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()))
        .build()

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/julienbanse/34cdfbd1c094b2dddffce2b5d5533d6b/raw/15b5f322838e08bf8a38985b7aa94f6c758d6741/"
    }
}