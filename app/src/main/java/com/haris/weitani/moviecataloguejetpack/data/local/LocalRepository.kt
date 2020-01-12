package com.haris.weitani.moviecataloguejetpack.data.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import org.jetbrains.anko.doAsync

class LocalRepository(mMovieDao : MovieDAO, mTvShowsDAO: TvShowsDAO){

    companion object{
        var INSTANCE : LocalRepository? = null
        fun getInstance(movieDAO: MovieDAO, tvShowsDAO: TvShowsDAO): LocalRepository{
            if(INSTANCE == null){
                INSTANCE = LocalRepository(movieDAO,tvShowsDAO)
            }
            return INSTANCE as LocalRepository
        }
    }

    var mMovieDAO: MovieDAO = mMovieDao
    var mTvShowsDAO : TvShowsDAO = mTvShowsDAO

    fun getPopularMovies(): LiveData<List<ResultGetMovie>?>? {
        val movies = MutableLiveData<List<ResultGetMovie>>()
        doAsync {
            movies.postValue(mMovieDAO.getAllMovie())
        }
        return movies
    }

    fun getMoviesById(movieId : Long) : LiveData<ResultGetMovie?>?{
        val movie = MutableLiveData<ResultGetMovie>()
        doAsync {
            movie.postValue(mMovieDAO.selectById(movieId))
        }
        return movie
    }

    fun insertMovies(datas : List<ResultGetMovie>){
        for(data in datas){
            mMovieDAO.insertMovie(data)
        }
    }

    fun getPopularTvShows(): LiveData<List<ResultTvShow>?>?{
        val tvshows = MutableLiveData<List<ResultTvShow>>()
        doAsync {
            tvshows.postValue(mTvShowsDAO.getAllTvShows())
        }
        return tvshows
    }

    fun getTvShowById(tvShowId : Long) : LiveData<ResultTvShow?>?{
        val tvShow = MutableLiveData<ResultTvShow>()
        doAsync {
            tvShow.postValue(mTvShowsDAO.selectById(tvShowId))
        }
        return tvShow
    }

    fun insertTvShows(datas : List<ResultTvShow>){
        for( data in datas){
            mTvShowsDAO.insertTvShows(data)
        }
    }
}