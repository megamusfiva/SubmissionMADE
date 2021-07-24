package com.example.core.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMoviesResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("results")
    val results: List<MoviesResponse>
)

data class MoviesResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,
)
