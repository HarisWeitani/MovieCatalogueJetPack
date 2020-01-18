package com.haris.weitani.moviecataloguejetpack.favoriteactivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haris.weitani.moviecataloguejetpack.data.CatalogueRepository
import com.haris.weitani.moviecataloguejetpack.data.local.MovieDatabase
import com.haris.weitani.moviecataloguejetpack.data.local.TVShowsDatabase
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import org.jetbrains.anko.doAsync

class FavoriteViewModel(private val mCatalogueRepository: CatalogueRepository) : ViewModel() {

    fun getFavMovies() = mCatalogueRepository.getAllFavMovies()
    fun getFavTvShows() = mCatalogueRepository.getAllFavTvShows()

}