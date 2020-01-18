package com.haris.weitani.moviecataloguejetpack.favoriteactivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haris.weitani.moviecataloguejetpack.data.local.MovieDatabase
import com.haris.weitani.moviecataloguejetpack.data.local.TVShowsDatabase
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import org.jetbrains.anko.doAsync

class FavoriteViewModel : ViewModel() {

    private val listMovie = MutableLiveData<ArrayList<ResultGetMovie>>()
    private val listTvShows = MutableLiveData<ArrayList<ResultTvShow>>()

    companion object {
        lateinit var movieDB: MovieDatabase
        lateinit var tvshowDB: TVShowsDatabase
    }

    internal fun setMovie(context: Context) {

        if (movieDB == null) {
            movieDB = MovieDatabase.getDatabase(context)
        }
        doAsync {
            listMovie.postValue(movieDB.movieDao().getAllFavMovie() as ArrayList<ResultGetMovie>?)
        }
    }

    internal fun getMovie(): LiveData<ArrayList<ResultGetMovie>> {
        return listMovie
    }

    internal fun setTvShows(context: Context) {
        if (tvshowDB == null) {
            tvshowDB = TVShowsDatabase.getDatabase(context)
        }
        doAsync {
            listTvShows.postValue(tvshowDB.tvShowsDao().getAllFavTvShows() as ArrayList<ResultTvShow>?)
        }
    }

    internal fun getTvShows(): LiveData<ArrayList<ResultTvShow>> {
        return listTvShows
    }

}