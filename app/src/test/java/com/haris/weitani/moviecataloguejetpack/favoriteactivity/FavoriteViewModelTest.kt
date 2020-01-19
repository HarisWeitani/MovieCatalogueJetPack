package com.haris.weitani.moviecataloguejetpack.favoriteactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito

class FavoriteViewModelTest {


    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var catalogueRepository = Mockito.mock(CatalogueRepository::class.java)

    @Before
    fun setUp() {
        favoriteViewModel = FavoriteViewModel(catalogueRepository)
    }

    @Test
    fun getFavMovies() {
        val dummyMovies = MutableLiveData<PagedList<ResultGetMovie>>()
        val pagedList: PagedList<ResultGetMovie> = Mockito.mock(PagedList::class.java) as PagedList<ResultGetMovie>
        dummyMovies.value = pagedList

        Mockito.`when`(catalogueRepository.getAllFavMovies()).thenReturn(dummyMovies)
        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<ResultGetMovie>>

        favoriteViewModel.getFavMovies().observeForever(observer)

        Mockito.verify(observer).onChanged(pagedList)
    }

    @Test
    fun getFavTvShows() {
        val dummyTvShows = MutableLiveData<PagedList<ResultTvShow>>()
        val pagedList: PagedList<ResultTvShow> = Mockito.mock(PagedList::class.java) as PagedList<ResultTvShow>
        dummyTvShows.value = pagedList

        Mockito.`when`(catalogueRepository.getAllFavTvShows()).thenReturn(dummyTvShows)
        val observer = Mockito.mock(Observer::class.java) as Observer<PagedList<ResultTvShow>>

        favoriteViewModel.getFavTvShows().observeForever(observer)

        Mockito.verify(observer).onChanged(pagedList)
    }
}