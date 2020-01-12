package com.haris.weitani.moviecataloguejetpack.mainactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.utils.FakeDummyData2
import com.haris.weitani.moviecataloguejetpack.vo.Resource
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel
    private var catalogueRepository = Mockito.mock(CatalogueRepository::class.java)

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(catalogueRepository)
    }

    @Test
    fun getMovie() {
        val resource : Resource<List<ResultGetMovie>?> = Resource.success(FakeDummyData2.generateDummyMovies())
        val dummyCourse : MutableLiveData<Resource<List<ResultGetMovie>?>> = MutableLiveData()
        val observer: Observer<Resource<List<ResultGetMovie>?>?> = Mockito.mock(Observer::class.java) as Observer<Resource<List<ResultGetMovie>?>?>

        dummyCourse.postValue(resource)

        Mockito.`when`(catalogueRepository.getPopularMovies()).thenReturn(dummyCourse)

        mainViewModel.getTestMovies()?.observeForever(observer)
        Mockito.verify(observer).onChanged(resource)
    }

    @Test
    fun getTvShow() {
        val resource : Resource<List<ResultTvShow>?> = Resource.success(FakeDummyData2.generateDummyTvShows())
        val dummyCourse : MutableLiveData<Resource<List<ResultTvShow>?>> = MutableLiveData()
        val observer: Observer<Resource<List<ResultTvShow>?>?> = Mockito.mock(Observer::class.java) as Observer<Resource<List<ResultTvShow>?>?>

        dummyCourse.postValue(resource)

        Mockito.`when`(catalogueRepository.getPopularTvShows()).thenReturn(dummyCourse)

        mainViewModel.getTestTvShows()?.observeForever(observer)
        Mockito.verify(observer).onChanged(resource)
    }
}