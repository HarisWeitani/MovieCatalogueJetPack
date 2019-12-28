package com.haris.weitani.moviecataloguejetpack.main_activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.data.DummyData
import com.haris.weitani.moviecataloguejetpack.data.Movie
import com.haris.weitani.moviecataloguejetpack.data.TvShow

class MainViewModel : ViewModel() {

    private val movieList = MutableLiveData<ArrayList<Movie>>()
    private val tvShowList = MutableLiveData<ArrayList<TvShow>>()

    internal fun setMovie(){
        movieList.postValue(initMovieDummyData())
    }

    internal fun getMovie() : LiveData<ArrayList<Movie>>{
        return movieList
    }

    private fun initMovieDummyData() : ArrayList<Movie>{
        return DummyData.generateDummyMovies()
    }

    internal fun setTvShow(){
        tvShowList.postValue(initTvShowDummyData())
    }

    internal fun getTvShow() : LiveData<ArrayList<TvShow>>{
        return tvShowList
    }

    private fun initTvShowDummyData() : ArrayList<TvShow>{
        return DummyData.generateDummyTvShows()
    }


}