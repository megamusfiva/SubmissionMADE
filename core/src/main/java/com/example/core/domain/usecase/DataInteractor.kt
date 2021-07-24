package com.example.core.domain.usecase

import com.example.core.domain.model.Data
import com.example.core.domain.repository.IDataRepository

class DataInteractor (private val dataRepository: IDataRepository) : DataUseCase{

    override fun getAllMovies() = dataRepository.getAllMovies()

    override fun getFavoriteMovies()= dataRepository.getFavoriteMovies()

    override fun setFavoriteMovies(movies: Data, state: Boolean) = dataRepository.setFavoriteMovies(movies, state)

    override fun getAllTvShow() = dataRepository.getAllTvShow()

    override fun getFavoriteTvShow()= dataRepository.getFavoriteTvShow()

    override fun setFavoriteTvShow(tvshow: Data, state: Boolean) = dataRepository.setFavoriteTvShow(tvshow, state)
}