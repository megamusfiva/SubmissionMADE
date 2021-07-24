package com.example.core.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.source.local.entity.MoviesEntity
import com.example.core.source.local.entity.TvShowEntity

@Database(entities = [MoviesEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}