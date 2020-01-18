package com.haris.weitani.moviecataloguejetpack.favoriteactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.haris.weitani.moviecataloguejetpack.FavoriteSectionPagerAdapter
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.data.local.MovieDatabase
import com.haris.weitani.moviecataloguejetpack.data.local.TVShowsDatabase
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    private lateinit var adapter : FavoriteSectionPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        adapter = FavoriteSectionPagerAdapter(this,supportFragmentManager)

        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> {
                this.finish()
            }
        }

        return true
    }
}
