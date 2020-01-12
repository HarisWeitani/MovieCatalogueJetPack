package com.haris.weitani.moviecataloguejetpack.detailview

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.ViewModelFactory
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.data.Movie
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.vo.Status
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.rv_layout_item_movie.view.*

class MovieDetailView : AppCompatActivity() {

    private lateinit var movieData: ResultGetMovie
    private lateinit var detailViewModel: DetailViewModel

    private var movieId : Int = 0

    companion object {

        fun newInstance(): Activity = MovieDetailView()

        private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
            val factory: ViewModelFactory? = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        movieId = intent.getIntExtra(GlobalVal.SELECTED_MOVIE, 0)
        initViewModel()

    }

    private fun initViewModel(){
        detailViewModel = obtainViewModel(this)
        detailViewModel.setMovieId(movieId.toLong())
        detailViewModel.movies?.observe(this, Observer {
            if(it!= null){
                when(it.status){
                    Status.SUCCESS ->{
                        movieData = it.data!!
                        initView()
                    }
                    Status.ERROR ->{

                    }
                    Status.LOADING ->{

                    }
                }
            }
        })
    }

    private fun initView() {
        Picasso.get()
            .load(GlobalVal.POSTER_BASE_URL + movieData.poster_path)
            .into(iv_poster_image)
        tv_movie_title.text = movieData.title
        tv_movie_description.text = movieData.overview
    }

}
