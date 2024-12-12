package com.bitul.newsapp.api

import com.bitul.newsapp.models.newsresponse
import com.bitul.newsapp.util.constants.Companion.API_KEYS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode: String = "in",
        @Query("page")
        pageNumber: Int = 1,
        @Query("category")
        category: String = "technology",
        @Query("apiKey")
        apiKey: String=  API_KEYS
    ):Response<newsresponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEYS
    ): Response<newsresponse>
}