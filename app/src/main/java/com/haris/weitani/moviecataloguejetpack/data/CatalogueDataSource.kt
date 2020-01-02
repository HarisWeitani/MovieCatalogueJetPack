package com.haris.weitani.moviecataloguejetpack.data

import androidx.lifecycle.LiveData

interface CatalogueDataSource{

    fun getPopularMovies() : LiveData<Any>

    fun getPopularTvShows() : LiveData<Any>

}