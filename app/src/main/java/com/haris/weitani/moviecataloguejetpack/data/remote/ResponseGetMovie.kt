package com.haris.weitani.moviecataloguejetpack.data.remote

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class ResponseGetMovie(
    var page: Int? = 0,
    var results: List<ResultGetMovie>? = listOf(),
    var total_pages: Int? = 0,
    var total_results: Int? = 0
)

@Entity
data class ResultGetMovie(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "overview")var overview: String? = "",
    @ColumnInfo(name = "popularity")var popularity: Double? = 0.0,
    @ColumnInfo(name = "poster_path")var poster_path: String? = "",
    @ColumnInfo(name = "title")var title: String? = "",
    @ColumnInfo(name = "is_favorite") var is_favorite : Boolean? = false
)