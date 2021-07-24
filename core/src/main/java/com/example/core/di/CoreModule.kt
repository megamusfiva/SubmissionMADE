package com.example.core.di

import androidx.room.Room
import com.example.core.domain.repository.IDataRepository
import com.example.core.source.DataRepository
import com.example.core.source.local.LocalDataSource
import com.example.core.source.local.room.DataDatabase
import com.example.core.source.remote.RemoteDataSource
import com.example.core.source.remote.api.ApiService
import com.example.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<DataDatabase>().dataDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DataDatabase::class.java, "Data.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IDataRepository> { DataRepository(get(), get(), get()) }
}