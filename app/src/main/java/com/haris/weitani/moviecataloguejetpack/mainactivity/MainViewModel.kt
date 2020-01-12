package com.haris.weitani.moviecataloguejetpack.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.data.DummyData
import com.haris.weitani.moviecataloguejetpack.data.Movie
import com.haris.weitani.moviecataloguejetpack.data.TvShow
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.vo.Resource

class MainViewModel(mCatalogueRepository: CatalogueRepository) : ViewModel() {

    private val catalogueRepository = mCatalogueRepository

    val movies : LiveData<Resource<List<ResultGetMovie>?>?>? = catalogueRepository.getPopularMovies()
    val tvShows : LiveData<Resource<List<ResultTvShow>?>?>? = catalogueRepository.getPopularTvShows()


    /**
     * Deprecated
     */
/*    private val movieList = MutableLiveData<ArrayList<Movie>>()
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
    }*/
}