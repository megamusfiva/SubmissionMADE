package com.example.submissionmade.tvShow

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Data
import com.example.core.domain.usecase.DataUseCase

class DetailTvShowViewModel(private val dataUseCase: DataUseCase) : ViewModel() {

    fun setFavoriteTvShow(data: Data, newStatus: Boolean) =
        dataUseCase.setFavoriteTvShow(data, newStatus)
}