package com.haris.weitani.moviecataloguejetpack.detailview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.utils.FakeDummyData2
import com.haris.weitani.moviecataloguejetpack.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var detailViewModel: DetailViewModel
    private var catalogueRepository = Mockito.mock(CatalogueRepository::class.java)
    private var dummyMovieId: Long = 419704
    private var dummyTvShowId: Long = 44217

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(catalogueRepository)
    }

    @Test
    fun getMovie() {
        val resource : Resource<ResultGetMovie?> = Resource.success(FakeDummyData2.generateDummyMovies()[0])
        val dummyCourse : MutableLiveData<Resource<ResultGetMovie?>> = MutableLiveData()
        val observer: Observer<Resource<ResultGetMovie?>?> = Mockito.mock(Observer::class.java) as Observer<Resource<ResultGetMovie?>?>

        dummyCourse.postValue(resource)

        Mockito.`when`(catalogueRepository.getMoviesById(dummyMovieId)).thenReturn(dummyCourse)

        detailViewModel.getTestMovie(dummyMovieId)?.observeForever(observer)
        Mockito.verify(observer).onChanged(resource)
    }

    @Test
    fun getTvShow() {
        val resource : Resource<ResultTvShow?> = Resource.success(FakeDummyData2.generateDummyTvShows()[0])
        val dummyCourse : MutableLiveData<Resource<ResultTvShow?>> = MutableLiveData()
        val observer: Observer<Resource<ResultTvShow?>?> = Mockito.mock(Observer::class.java) as Observer<Resource<ResultTvShow?>?>

        dummyCourse.postValue(resource)

        Mockito.`when`(catalogueRepository.getTvShowsById(dummyTvShowId)).thenReturn(dummyCourse)

        detailViewModel.getTestTvShows(dummyTvShowId)?.observeForever(observer)
        Mockito.verify(observer).onChanged(resource)
    }
}