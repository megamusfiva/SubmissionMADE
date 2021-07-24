package com.example.core.source.local.room

import androidx.room.*
import com.example.core.source.local.entity.MoviesEntity
import com.example.core.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DataDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movies where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(data: List<MoviesEntity>)

    @Update
    fun updateFavoriteMovies(data: MoviesEntity)

    @Query("SELECT * FROM tvshow")
    fun getAllTvShow():  Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tvshow where isFavorite = 1")
    fun getFavoriteTvShow():  Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(data: List<TvShowEntity>)

    @Update
    fun updateFavoriteTvShow(data: TvShowEntity)
}