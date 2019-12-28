package com.haris.weitani.moviecataloguejetpack.data

import com.haris.weitani.moviecataloguejetpack.R

class DummyData {
    companion object {

        fun generateDummyMovies(): ArrayList<Movie> {
            val tempMovieList = ArrayList<Movie>()
            tempMovieList.add(Movie(0, R.drawable.poster_a_start_is_born,"A Star Is Born", R.string.a_star_is_born))
            tempMovieList.add(Movie(1, R.drawable.poster_alita,"Alita Battle Angel", R.string.alita_battle_angel))
            tempMovieList.add(Movie(2, R.drawable.poster_aquaman,"Aquaman", R.string.aquaman))
            tempMovieList.add(Movie(3, R.drawable.poster_master_z,"Master Z", R.string.master_z))
            tempMovieList.add(Movie(4, R.drawable.poster_serenity,"Serenity", R.string.serenity))
            tempMovieList.add(Movie(5, R.drawable.poster_infinity_war,"Avengers Infinity War", R.string.avengers))
            tempMovieList.add(Movie(6, R.drawable.poster_bohemian,"Bohemian Rhapsody", R.string.bohemian))
            tempMovieList.add(Movie(7, R.drawable.poster_creed,"Creed", R.string.creed))
            tempMovieList.add(Movie(8, R.drawable.poster_robin_hood,"Robin Hood", R.string.robin_hood))
            tempMovieList.add(Movie(9, R.drawable.poster_how_to_train,"How to Train Your Dragon", R.string.dragon))
            return tempMovieList
        }

        fun generateDummyTvShows() : ArrayList<TvShow>{
            val tempTvShowList = ArrayList<TvShow>()
            tempTvShowList.add(TvShow(0,R.drawable.poster_arrow,"Arrow", R.string.arrow))
            tempTvShowList.add(TvShow(1,R.drawable.poster_doom_patrol,"Doom Patrol",R.string.doom_patrol))
            tempTvShowList.add(TvShow(2,R.drawable.poster_fairytail,"Fairy Tail",R.string.fairy_tail))
            tempTvShowList.add(TvShow(3,R.drawable.poster_family_guy,"Family Guy",R.string.family_guy))
            tempTvShowList.add(TvShow(4,R.drawable.poster_flash,"The Flash",R.string.the_flash))
            tempTvShowList.add(TvShow(5,R.drawable.poster_got,"GOT",R.string.got))
            tempTvShowList.add(TvShow(6,R.drawable.poster_gotham,"Gotham",R.string.gotham))
            tempTvShowList.add(TvShow(7,R.drawable.poster_hanna,"Hanna",R.string.hanna))
            tempTvShowList.add(TvShow(8,R.drawable.poster_iron_fist,"Iron Fist",R.string.iron_fist))
            tempTvShowList.add(TvShow(9,R.drawable.poster_naruto_shipudden,"Naruto Shippuden",R.string.naruto_shippuden))
            return tempTvShowList
        }

    }
}