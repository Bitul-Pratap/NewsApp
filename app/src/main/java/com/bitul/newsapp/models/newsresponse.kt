package com.bitul.newsapp.models

data class newsresponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)