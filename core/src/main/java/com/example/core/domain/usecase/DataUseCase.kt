package com.example.core.domain.usecase

import com.example.core.domain.model.Data
import com.example.core.source.Resource
import kotlinx.coroutines.flow.Flow

interface DataUseCase {
    fun getAllMovies(): Flow<Resource<List<Data>>>

    fun getFavoriteMovies(): Flow<List<Data>>

    fun setFavoriteMovies(movies: Data, state: Boolean)

    fun getAllTvShow(): Flow<Resource<List<Data>>>

    fun getFavoriteTvShow(): Flow<List<Data>>

    fun setFavoriteTvShow(tvshow: Data, state: Boolean)
}