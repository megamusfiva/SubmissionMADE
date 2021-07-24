package com.example.core.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("results")
    val tvshow: List<TvShowResponse>
)
data class TvShowResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("first_air_date")
    val releaseDate: String,

    @field:SerializedName("overview")
    val overview: String,
)