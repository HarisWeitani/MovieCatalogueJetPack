package com.haris.weitani.moviecataloguejetpack.detailview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.data.DummyData
import com.haris.weitani.moviecataloguejetpack.data.Movie
import com.haris.weitani.moviecataloguejetpack.data.TvShow
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.vo.Resource
import org.jetbrains.annotations.TestOnly

class DetailViewModel(mCatalogueRepository: CatalogueRepository) : ViewModel() {

    private val catalogueRepository = mCatalogueRepository

    private var movieId = MutableLiveData<Long>()
    internal fun setMovieId(movieId: Long) {
        this.movieId.postValue(movieId)
    }
    val movies: LiveData<Resource<ResultGetMovie?>?>? = Transformations.switchMap(movieId){
        catalogueRepository.getMoviesById(it)
    }

    private var tvShowId = MutableLiveData<Long>()
    internal fun setTvShowId(tvShowId : Long){
        this.tvShowId.postValue(tvShowId)
    }
    val tvShows: LiveData<Resource<ResultTvShow?>?>? = Transformations.switchMap(tvShowId){
        catalogueRepository.getTvShowsById(it)
    }

    internal fun addMovieFavorite(data : ResultGetMovie){
        catalogueRepository.addFavMovie(data)
    }

    internal fun removeMovieFavorite(data : ResultGetMovie){
        catalogueRepository.removeFavMovie(data)
    }

    internal fun addTvShowFavorite(data: ResultTvShow){
        catalogueRepository.addFavTvShow(data)
    }

    internal fun removeTvShowFavorite(data : ResultTvShow){
        catalogueRepository.removeFavTvShow(data)
    }

    @TestOnly
    fun getTestMovie(movieId: Long) : LiveData<Resource<ResultGetMovie?>?>?{
        return catalogueRepository.getMoviesById(movieId)
    }

    @TestOnly
    fun getTestTvShows(tvShowId : Long) : LiveData<Resource<ResultTvShow?>?>?{
        return catalogueRepository.getTvShowsById(tvShowId)
    }
}