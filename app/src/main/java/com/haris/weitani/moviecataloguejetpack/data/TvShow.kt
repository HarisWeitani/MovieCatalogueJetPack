package com.haris.weitani.moviecataloguejetpack.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow (
    val id : Int,
    val picture : Int,
    val name : String,
    val desc : Int
): Parcelable