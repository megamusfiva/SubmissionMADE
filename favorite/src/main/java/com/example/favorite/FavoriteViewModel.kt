package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.DataUseCase

class FavoriteViewModel (dataUseCase: DataUseCase) : ViewModel() {

    val favoriteMovies =  dataUseCase.getFavoriteMovies().asLiveData()

    val favoriteTvShow = dataUseCase.getFavoriteTvShow().asLiveData()
}
