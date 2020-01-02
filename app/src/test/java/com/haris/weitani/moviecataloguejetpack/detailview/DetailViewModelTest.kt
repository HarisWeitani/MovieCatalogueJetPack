package com.haris.weitani.moviecataloguejetpack.detailview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.data.Movie
import com.haris.weitani.moviecataloguejetpack.data.TvShow
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dummyMovie : Movie
    private lateinit var dummyTvShow : TvShow

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel()
        dummyMovie = Movie(1, R.drawable.poster_alita,"Alita Battle Angel", R.string.alita_battle_angel)
        dummyTvShow = TvShow(1,R.drawable.poster_doom_patrol,"Doom Patrol",R.string.doom_patrol)
    }

    @Test
    fun getMovie() {
        detailViewModel.setMovieById(1)
        detailViewModel.getMovieById().observeForever {
            assertEquals(dummyMovie.id,it.id)
            assertEquals(dummyMovie.picture,it.picture)
            assertEquals(dummyMovie.name,it.name)
            assertEquals(dummyMovie.desc,it.desc)
        }
    }

    @Test
    fun getTvShow() {
        detailViewModel.setTvShowById(1)
        detailViewModel.getTvShowById().observeForever{
            assertEquals(dummyTvShow.id,it.id)
            assertEquals(dummyTvShow.picture,it.picture)
            assertEquals(dummyTvShow.name,it.name)
            assertEquals(dummyTvShow.desc,it.desc)
        }
    }
}