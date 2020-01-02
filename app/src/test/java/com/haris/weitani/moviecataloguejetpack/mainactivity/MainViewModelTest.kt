package com.haris.weitani.moviecataloguejetpack.mainactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

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
            assertEquals(10,it.size)
        }
    }

    @Test
    fun getTvShow() {
        mainViewModel.setTvShow()
        mainViewModel.getTvShow().observeForever{
            assertEquals(10,it.size)
        }
    }
}