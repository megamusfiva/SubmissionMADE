package com.example.core.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshow")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int?= null,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String?= null,

    @ColumnInfo(name = "name")
    var name: String?= null,

    @ColumnInfo(name = "posterPath")
    var posterPath: String?= null,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double?= null,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?= null,

    @ColumnInfo(name = "overview")
    var overview: String?= null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)