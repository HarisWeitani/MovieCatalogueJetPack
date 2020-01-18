package com.haris.weitani.moviecataloguejetpack.mainactivity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.haris.weitani.moviecataloguejetpack.MainSectionPagerAdapter
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.favoriteactivity.FavoriteActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    private lateinit var adapterMain : MainSectionPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapterMain =
            MainSectionPagerAdapter(
                this,
                supportFragmentManager
            )
        view_pager.adapter = adapterMain
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_favorite -> {
                startActivity(intentFor<FavoriteActivity>())
            }
        }
        return super.onOptionsItemSelected(item)
    }

}