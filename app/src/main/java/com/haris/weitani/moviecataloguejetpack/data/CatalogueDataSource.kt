package com.haris.weitani.moviecataloguejetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.haris.weitani.moviecataloguejetpack.vo.Resource
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow

interface CatalogueDataSource{

    fun getPopularMovies() : LiveData<Resource<List<ResultGetMovie>?>?>?

    fun getMoviesById(movieId : Long) : LiveData<Resource<ResultGetMovie?>?>?

    fun getPopularTvShows() : LiveData<Resource<List<ResultTvShow>?>?>?

    fun getTvShowsById(tvShowId : Long) : LiveData<Resource<ResultTvShow?>?>?

    fun getAllFavMovies() : LiveData<PagedList<ResultGetMovie>>

    fun addFavMovie(data : ResultGetMovie)

    fun removeFavMovie(data : ResultGetMovie)

    fun getAllFavTvShows() : LiveData<PagedList<ResultTvShow>>

    fun addFavTvShow(data : ResultTvShow)

    fun removeFavTvShow(data : ResultTvShow)

}