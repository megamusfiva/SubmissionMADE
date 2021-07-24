package com.example.core.source.remote.api

import com.example.core.BuildConfig
import com.example.core.source.remote.response.ListMoviesResponse
import com.example.core.source.remote.response.ListTvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getAllMovies(
        @Query("api_key") api: String? = BuildConfig.API_KEY
    ): ListMoviesResponse

    @GET("tv/on_the_air")
    suspend fun getAllTvShow(
        @Query("api_key") api: String? = BuildConfig.API_KEY
    ): ListTvShowResponse
}