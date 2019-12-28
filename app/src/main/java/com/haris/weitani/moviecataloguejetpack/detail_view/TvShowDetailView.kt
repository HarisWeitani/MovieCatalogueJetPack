package com.haris.weitani.moviecataloguejetpack.detail_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.data.TvShow
import kotlinx.android.synthetic.main.activity_tv_show_detail_view.*

class TvShowDetailView : AppCompatActivity() {

    private lateinit var tvShow: TvShow
    private lateinit var detailViewModel: DetailViewModel

    private var tvShowId: Int = 0

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail_view)

        tvShowId = intent.getIntExtra(GlobalVal.SELECTED_TV_SHOW, 0)

        initViewModel()
    }

    private fun initViewModel() {
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        detailViewModel.setTvShowById(tvShowId)
        detailViewModel.getTvShowById().observe(this, Observer {
            tvShow = it
            initView()
        })
    }

    private fun initView() {
        iv_poster_image.setImageResource(tvShow.picture)
        tv_tvshow_title.text = tvShow.name
        tv_tvshow_description.setText(tvShow.desc)
    }

}
