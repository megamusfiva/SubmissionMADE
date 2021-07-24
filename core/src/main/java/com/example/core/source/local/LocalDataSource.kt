package com.example.core.source.local

import com.example.core.source.local.entity.MoviesEntity
import com.example.core.source.local.entity.TvShowEntity
import com.example.core.source.local.room.DataDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dataDao: DataDao) {

    fun getAllMovies(): Flow<List<MoviesEntity>> = dataDao.getAllMovies()

    fun getFavoriteMovies():  Flow<List<MoviesEntity>> = dataDao.getFavoriteMovies()

    suspend fun insertMovies(moviesList: List<MoviesEntity>) = dataDao.insertMovies(moviesList)

    fun setFavoriteMovies(movies: MoviesEntity, newState: Boolean) {
        movies.isFavorite = newState
        dataDao.updateFavoriteMovies(movies)
    }

    fun getAllTvShow():  Flow<List<TvShowEntity>> = dataDao.getAllTvShow()

    fun getFavoriteTvShow():  Flow<List<TvShowEntity>> = dataDao.getFavoriteTvShow()

    suspend fun insertTvShow(tvshowList: List<TvShowEntity>) = dataDao.insertTvShow(tvshowList)

    fun setFavoriteTvShow(tvshow: TvShowEntity, newState: Boolean) {
        tvshow.isFavorite = newState
        dataDao.updateFavoriteTvShow(tvshow)
    }

}