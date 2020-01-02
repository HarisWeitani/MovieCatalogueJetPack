package com.haris.weitani.moviecataloguejetpack.detailview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.data.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailView : AppCompatActivity() {

    private lateinit var movieData: Movie
    private lateinit var detailViewModel: DetailViewModel

    private var movieId : Int = 0

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        movieId = intent.getIntExtra(GlobalVal.SELECTED_MOVIE, 0)
        initViewModel()

    }

    private fun initViewModel(){
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        detailViewModel.setMovieById(movieId)
        detailViewModel.getMovieById().observe(this, Observer {
            movieData = it
            initView()
        })
    }

    private fun initView() {
        iv_poster_image.setImageResource(movieData.picture)
        tv_movie_title.text = movieData.name
        tv_movie_description.setText(movieData.desc)
    }

}
