package com.example.submissionmade.movies

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Data
import com.example.core.domain.usecase.DataUseCase

class DetailMoviesViewModel(private val dataUseCase: DataUseCase) : ViewModel() {

    fun setFavoriteMovies(data: Data, newStatus: Boolean) =
        dataUseCase.setFavoriteMovies(data, newStatus)
}