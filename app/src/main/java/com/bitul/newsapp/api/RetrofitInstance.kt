package com.bitul.newsapp.api

import com.bitul.newsapp.util.constants.Companion.BASE_URL
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{

        private val retrofit by lazy {
            val logging=HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client:okhttp3.OkHttpClient=okhttp3.OkHttpClient.Builder() .addInterceptor(logging).build()

          Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
        }
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }

    }
}