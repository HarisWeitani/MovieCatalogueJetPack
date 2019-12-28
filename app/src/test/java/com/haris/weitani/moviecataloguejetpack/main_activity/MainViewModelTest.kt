package com.haris.weitani.moviecataloguejetpack.main_activity

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.haris.weitani.moviecataloguejetpack.data.Movie
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito

class MainViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainViewModel = MainViewModel()
    }

    @Test
    fun getMovie() {
        mainViewModel.setMovie()
        mainViewModel.getMovie().observeForever {
            assertEquals(it.size,10)
        }
    }

    @Test
    fun getTvShow() {
        mainViewModel.setTvShow()
        mainViewModel.getTvShow().observeForever{
            assertEquals(it.size,10)
        }
    }
}