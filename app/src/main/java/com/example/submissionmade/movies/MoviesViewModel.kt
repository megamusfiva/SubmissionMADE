package com.example.submissionmade.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.DataUseCase

class MoviesViewModel(dataUseCase: DataUseCase)  : ViewModel() {
    val data = dataUseCase.getAllMovies().asLiveData()
}
