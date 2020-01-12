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
import com.haris.weitani.moviecataloguejetpack.data.TvShow
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.vo.Status
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tv_show_detail_view.*

class TvShowDetailView : AppCompatActivity() {

    private lateinit var tvShow: ResultTvShow
    private lateinit var detailViewModel: DetailViewModel

    private var tvShowId: Int = 0

    companion object {

        fun newInstance(): Activity = TvShowDetailView()

        private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
            val factory: ViewModelFactory? = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail_view)

        tvShowId = intent.getIntExtra(GlobalVal.SELECTED_TV_SHOW, 0)

        initViewModel()
    }

    private fun initViewModel() {
        isLoading(true)
        detailViewModel = obtainViewModel(this)
        detailViewModel.setTvShowId(tvShowId.toLong())
        detailViewModel.tvShows?.observe(this, Observer {
            if(it!= null){
                when(it.status){
                    Status.SUCCESS ->{
                        isLoading(false)
                        tvShow = it.data!!
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
            .load(GlobalVal.POSTER_BASE_URL + tvShow.poster_path)
            .into(iv_poster_image)

        tv_tvshow_title.text = tvShow.name
        tv_tvshow_description.text = tvShow.overview
    }

    private fun isLoading(status : Boolean){
        if(status){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }
    }

}
