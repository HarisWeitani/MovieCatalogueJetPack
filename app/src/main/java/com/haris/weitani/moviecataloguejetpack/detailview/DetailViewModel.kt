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

    @TestOnly
    fun getTestMovie(movieId: Long) : LiveData<Resource<ResultGetMovie?>?>?{
        return catalogueRepository.getMoviesById(movieId)
    }

    @TestOnly
    fun getTestTvShows(tvShowId : Long) : LiveData<Resource<ResultTvShow?>?>?{
        return catalogueRepository.getTvShowsById(tvShowId)
    }


    /**
     * Deprecated
     */
/*    private val movie = MutableLiveData<Movie>()
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
    }*/

}