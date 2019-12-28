package com.haris.weitani.moviecataloguejetpack.detail_view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
    }

    @Test
    fun getMovie() {
        detailViewModel.setMovieById(1)
        detailViewModel.getMovieById().observeForever {
            assertEquals(it.id,1)
        }
    }

    @Test
    fun getTvShow() {
        detailViewModel.setTvShowById(1)
        detailViewModel.getTvShowById().observeForever{
            assertEquals(it.id,1)
        }
    }
}