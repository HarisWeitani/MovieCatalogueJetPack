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
import org.jetbrains.annotations.TestOnly

class MainViewModel(mCatalogueRepository: CatalogueRepository) : ViewModel() {

    private val catalogueRepository = mCatalogueRepository

    val movies : LiveData<Resource<List<ResultGetMovie>?>?>? = catalogueRepository.getPopularMovies()
    val tvShows : LiveData<Resource<List<ResultTvShow>?>?>? = catalogueRepository.getPopularTvShows()

    @TestOnly
    fun getTestMovies() : LiveData<Resource<List<ResultGetMovie>?>?>?{
        return catalogueRepository.getPopularMovies()
    }

    @TestOnly
    fun getTestTvShows() : LiveData<Resource<List<ResultTvShow>?>?>?{
        return catalogueRepository.getPopularTvShows()
    }

}