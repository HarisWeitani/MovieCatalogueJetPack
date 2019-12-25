package com.haris.weitani.moviecataloguejetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapterMain : MainSectionPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapterMain = MainSectionPagerAdapter(
            this,
            supportFragmentManager
        )
        view_pager.adapter = adapterMain
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f

    }


}