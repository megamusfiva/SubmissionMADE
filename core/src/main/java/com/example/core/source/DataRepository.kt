package com.example.core.source

import com.example.core.domain.model.Data
import com.example.core.domain.repository.IDataRepository
import com.example.core.source.local.LocalDataSource
import com.example.core.source.remote.RemoteDataSource
import com.example.core.source.remote.api.ApiResponse
import com.example.core.source.remote.response.MoviesResponse
import com.example.core.source.remote.response.TvShowResponse
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IDataRepository {

    override fun getAllMovies(): Flow<Resource<List<Data>>> =
        object : NetworkBoundResource<List<Data>, List<MoviesResponse>>() {
            override fun loadFromDB(): Flow<List<Data>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomainMovies(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MoviesResponse>) {
                val moviesList = DataMapper.mapResponsesToEntitiesMovies(data)
                localDataSource.insertMovies(moviesList)
            }

            override fun shouldFetch(data: List<Data>?): Boolean =
                data == null || data.isEmpty()
            //ganti dengan true jika ingin selalu mengambil data dari internet

        }.asFlow()

    override fun getFavoriteMovies():  Flow<List<Data>>  {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomainMovies(it)
        }
    }

    override fun setFavoriteMovies(movies: Data, state: Boolean) {
        val moviesEntity = DataMapper.mapDomainToEntityMovies(movies)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(moviesEntity, state) }
    }

    override fun getAllTvShow(): Flow<Resource<List<Data>>> =
        object : NetworkBoundResource<List<Data>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<Data>> {
                return localDataSource.getAllTvShow().map {
                    DataMapper.mapEntitiesToDomainTvShow(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShow()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvshowList = DataMapper.mapResponsesToEntitiesTvShow(data)
                localDataSource.insertTvShow(tvshowList)
            }

            override fun shouldFetch(data: List<Data>?): Boolean =
               data == null || data.isEmpty()
            //ganti dengan true jika ingin selalu mengambil data dari internet

        }.asFlow()

    override fun getFavoriteTvShow():  Flow<List<Data>>  {
        return localDataSource.getFavoriteTvShow().map {
            DataMapper.mapEntitiesToDomainTvShow(it)
        }
    }

    override fun setFavoriteTvShow(tvshow: Data, state: Boolean) {
        val tvshowEntity = DataMapper.mapDomainToEntityTvShow(tvshow)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvshowEntity, state) }
    }
}