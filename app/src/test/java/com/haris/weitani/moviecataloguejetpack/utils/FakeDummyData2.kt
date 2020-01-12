package com.haris.weitani.moviecataloguejetpack.utils

import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.data.Movie
import com.haris.weitani.moviecataloguejetpack.data.TvShow
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow

class FakeDummyData2 {
    companion object {

        fun generateDummyMovies(): ArrayList<ResultGetMovie> {
            val tempMovieList = ArrayList<ResultGetMovie>()
            tempMovieList.add(ResultGetMovie(1,null,null,null,null,false))
            return tempMovieList
        }

        fun generateDummyTvShows() : ArrayList<ResultTvShow>{
            val tempTvShowList = ArrayList<ResultTvShow>()
            tempTvShowList.add(ResultTvShow(1,null,null,null,null,false))
            return tempTvShowList
        }

    }
}