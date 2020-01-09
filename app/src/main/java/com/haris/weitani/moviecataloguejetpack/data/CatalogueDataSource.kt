package com.haris.weitani.moviecataloguejetpack.data

import androidx.lifecycle.LiveData
import com.haris.weitani.moviecataloguejetpack.vo.Resource
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow

interface CatalogueDataSource{

    fun getPopularMovies() : LiveData<Resource<List<ResultGetMovie>?>?>?

    fun getPopularTvShows() : LiveData<Resource<List<ResultTvShow>?>?>?

}