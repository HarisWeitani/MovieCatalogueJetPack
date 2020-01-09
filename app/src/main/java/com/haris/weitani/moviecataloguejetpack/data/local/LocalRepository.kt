package com.haris.weitani.moviecataloguejetpack.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow

class LocalRepository(mMovieDao : MovieDAO, mTvShowsDAO: TvShowsDAO){

    companion object{
        lateinit var INSTANCE : LocalRepository
        fun getInstance(movieDAO: MovieDAO, tvShowsDAO: TvShowsDAO): LocalRepository{
            if(INSTANCE == null){
                INSTANCE = LocalRepository(movieDAO,tvShowsDAO)
            }
            return INSTANCE
        }
    }

    var mMovieDAO: MovieDAO = mMovieDao
    var mTvShowsDAO : TvShowsDAO = mTvShowsDAO

    fun getPopularMovies(): LiveData<List<ResultGetMovie>?>? {
        val movies = MutableLiveData<List<ResultGetMovie>>()
        movies.postValue(mMovieDAO.getAllMovie())
        return movies
    }

    fun insertMovies(datas : List<ResultGetMovie>){
        for(data in datas){
            mMovieDAO.insertMovie(data)
        }
    }

    fun getPopularTvShows(): LiveData<List<ResultTvShow>?>?{
        val tvshows = MutableLiveData<List<ResultTvShow>>()
        tvshows.postValue(mTvShowsDAO.getAllTvShows())
        return tvshows
    }

    fun insertTvShows(datas : List<ResultTvShow>){
        for( data in datas){
            mTvShowsDAO.insertTvShows(data)
        }
    }
}