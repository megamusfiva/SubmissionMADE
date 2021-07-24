package com.example.submissionmade.di

import com.example.core.domain.usecase.DataInteractor
import com.example.core.domain.usecase.DataUseCase
import com.example.submissionmade.movies.DetailMoviesViewModel
import com.example.submissionmade.movies.MoviesViewModel
import com.example.submissionmade.tvShow.DetailTvShowViewModel
import com.example.submissionmade.tvShow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<DataUseCase> { DataInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { DetailMoviesViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
   // viewModel { com.example.favorite.FavoriteViewModel(get()) }
}