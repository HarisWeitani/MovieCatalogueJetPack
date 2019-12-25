package com.haris.weitani.moviecataloguejetpack.main_activity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.data.Movie
import com.haris.weitani.moviecataloguejetpack.data.TvShow

class MainViewModel : ViewModel() {

    private val movie = MutableLiveData<ArrayList<Movie>>()
    private val tvShow = MutableLiveData<ArrayList<TvShow>>()

    internal fun setMovie(){
        movie.postValue(initMovieDummyData())
    }

    internal fun getMovie() : LiveData<ArrayList<Movie>>{
        return movie
    }

    private fun initMovieDummyData() : ArrayList<Movie>{
        val tempMovieList = ArrayList<Movie>()
        tempMovieList.add(Movie(0,R.drawable.poster_a_start_is_born,"A Star Is Born", R.string.a_star_is_born))
        tempMovieList.add(Movie(1,R.drawable.poster_alita,"Alita Battle Angel",R.string.alita_battle_angel))
        tempMovieList.add(Movie(2,R.drawable.poster_aquaman,"Aquaman",R.string.aquaman))
        tempMovieList.add(Movie(3,R.drawable.poster_master_z,"Master Z",R.string.master_z))
        tempMovieList.add(Movie(4,R.drawable.poster_serenity,"Serenity",R.string.serenity))
        return tempMovieList
    }

    internal fun setTvShow(){
        tvShow.postValue(initTvShowDummyData())
    }

    internal fun getTvShow() : LiveData<ArrayList<TvShow>>{
        return tvShow
    }

    private fun initTvShowDummyData() : ArrayList<TvShow>{
        val tempTvShowList = ArrayList<TvShow>()
        tempTvShowList.add(TvShow(R.drawable.poster_arrow,"Arrow", R.string.arrow))
        tempTvShowList.add(TvShow(R.drawable.poster_doom_patrol,"Doom Patrol",R.string.doom_patrol))
        tempTvShowList.add(TvShow(R.drawable.poster_fairytail,"Fairy Tail",R.string.fairy_tail))
        tempTvShowList.add(TvShow(R.drawable.poster_family_guy,"Family Guy",R.string.family_guy))
        tempTvShowList.add(TvShow(R.drawable.poster_flash,"The Flash",R.string.the_flash))
        return tempTvShowList
    }


}