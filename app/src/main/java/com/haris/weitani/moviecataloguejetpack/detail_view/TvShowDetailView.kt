package com.haris.weitani.moviecataloguejetpack.detail_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.data.TvShow
import kotlinx.android.synthetic.main.activity_tv_show_detail_view.*

class TvShowDetailView : AppCompatActivity() {

    private lateinit var tvShowData: TvShow

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail_view)

        tvShowData = intent.getParcelableExtra(GlobalVal.SELECTED_MOVIE)

        initView()
    }

    private fun initView() {
        iv_poster_image.setImageResource(tvShowData.picture)

        tv_tvshow_title.text = tvShowData.name
//        tv_tvshow_description.text = tvShowData.desc
    }

}
