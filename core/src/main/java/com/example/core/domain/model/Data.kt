package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class Data(
    var id: Int?= null,
    var backdropPath: String?= null,
    var title: String?= null,
    var posterPath: String?= null,
    var voteAverage: Double?= null,
    var releaseDate: String?= null,
    var overview: String?= null,
    var isFavorite: Boolean
): Parcelable