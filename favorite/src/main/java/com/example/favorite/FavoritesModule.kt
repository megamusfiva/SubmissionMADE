package com.example.favorite
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FavoriteViewModel(get()) }
}