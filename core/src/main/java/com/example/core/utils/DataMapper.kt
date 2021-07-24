package com.example.core.utils

import com.example.core.domain.model.Data
import com.example.core.source.local.entity.MoviesEntity
import com.example.core.source.local.entity.TvShowEntity
import com.example.core.source.remote.response.MoviesResponse
import com.example.core.source.remote.response.TvShowResponse

object DataMapper {
    fun mapResponsesToEntitiesMovies(input: List<MoviesResponse>): List<MoviesEntity> {
        val moviesList = ArrayList<MoviesEntity>()
        input.map {
            val data = MoviesEntity(
                id = it.id,
                backdropPath = it.backdropPath,
                title = it.title,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                overview = it.overview,
                isFavorite = false
            )
            moviesList.add(data)
        }
        return moviesList
    }
    fun mapEntitiesToDomainMovies(input: List<MoviesEntity>): List<Data> =
        input.map {
            Data(
                id = it.id,
                backdropPath = it.backdropPath,
                title = it.title,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                overview = it.overview,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntityMovies(input: Data) = MoviesEntity(
        id = input.id,
        backdropPath = input.backdropPath,
        title = input.title,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
        releaseDate = input.releaseDate,
        overview = input.overview,
        isFavorite = input.isFavorite
    )
    fun mapResponsesToEntitiesTvShow(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val data = TvShowEntity(
                id = it.id,
                backdropPath = it.backdropPath,
                name = it.name,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                overview = it.overview,
                isFavorite = false
            )
            tvShowList.add(data)
        }
        return tvShowList
    }
    fun mapEntitiesToDomainTvShow(input: List<TvShowEntity>): List<Data> =
        input.map {
            Data(
                id = it.id,
                backdropPath = it.backdropPath,
                title = it.name,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                overview = it.overview,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntityTvShow(input: Data) = TvShowEntity(
        id = input.id,
        backdropPath = input.backdropPath,
        name = input.title,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
        releaseDate = input.releaseDate,
        overview = input.overview,
        isFavorite = input.isFavorite
    )
}