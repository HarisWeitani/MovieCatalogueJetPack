package com.haris.weitani.moviecataloguejetpack.detailview

import android.app.Activity
import android.os.Bundle
import android.view.View
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
import org.jetbrains.anko.doAsync

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
        isLoading(true)
        detailViewModel = obtainViewModel(this)
        detailViewModel.setMovieId(movieId.toLong())
        detailViewModel.movies?.observe(this, Observer {
            if(it!= null){
                when(it.status){
                    Status.SUCCESS ->{
                        isLoading(false)
                        movieData = it.data!!
                        initView()
                    }
                    Status.ERROR ->{
                        isLoading(false)
                    }
                    Status.LOADING ->{
                        isLoading(true)
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

        if(movieData.is_favorite == null){
            movieData.is_favorite = false
        }

        if(movieData.is_favorite!!){
            iv_favorite.setImageResource(R.drawable.ic_favorite_red_24dp)
        }else{
            iv_favorite.setImageResource(R.drawable.ic_favorite_border_red_24dp)
        }

        iv_favorite.setOnClickListener {
            validateFavorite()
        }

    }

    private fun validateFavorite(){
        if(movieData.is_favorite!!){
            iv_favorite.setImageResource(R.drawable.ic_favorite_border_red_24dp)
            movieData.is_favorite = false
            doAsync {
                detailViewModel.removeMovieFavorite(movieData)
            }
        }else{
            iv_favorite.setImageResource(R.drawable.ic_favorite_red_24dp)
            movieData.is_favorite = true
            doAsync {
                detailViewModel.addMovieFavorite(movieData)
            }
        }
    }

    private fun isLoading(status : Boolean){
        if(status){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }
    }
}
