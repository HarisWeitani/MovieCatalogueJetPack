package com.haris.weitani.moviecataloguejetpack.detail_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.data.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailView : AppCompatActivity() {

    private lateinit var movieData: Movie

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        movieData = intent.getParcelableExtra(GlobalVal.SELECTED_MOVIE)

        initView()
    }

    private fun initView() {
        iv_poster_image.setImageResource(movieData.picture)
        tv_movie_title.text = movieData.name
        tv_movie_description.text = movieData.desc
    }

}
