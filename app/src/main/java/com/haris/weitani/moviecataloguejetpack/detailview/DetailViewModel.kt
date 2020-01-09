package com.haris.weitani.moviecataloguejetpack.detailview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.data.DummyData
import com.haris.weitani.moviecataloguejetpack.data.Movie
import com.haris.weitani.moviecataloguejetpack.data.TvShow

class DetailViewModel(mCatalogueRepository: CatalogueRepository) : ViewModel() {

    private val catalogueRepository = mCatalogueRepository

    private val movie = MutableLiveData<Movie>()
    private val tvShow = MutableLiveData<TvShow>()

    internal fun setMovieById(movieId: Int) {
        for (data in DummyData.generateDummyMovies()) {
            if (data.id == movieId) {
                movie.postValue(data)
            }
        }
    }

    internal fun getMovieById(): LiveData<Movie> {
        return movie
    }

    internal fun setTvShowById(tvShowId: Int) {
        for (data in DummyData.generateDummyTvShows()) {
            if (data.id == tvShowId) {
                tvShow.postValue(data)
            }
        }
    }

    internal fun getTvShowById(): LiveData<TvShow> {
        return tvShow
    }


}