package com.example.submissionmade.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.DataUseCase

class TvShowViewModel(dataUseCase: DataUseCase) : ViewModel() {
    val data = dataUseCase.getAllTvShow().asLiveData()
}

