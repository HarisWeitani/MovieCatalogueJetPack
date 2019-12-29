package com.haris.weitani.moviecataloguejetpack.detail_view

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.utils.FakeDummyData
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TvShowDetailViewTest {

    private var tvShowDummy = FakeDummyData.generateDummyTvShows()[1]

    @get:Rule
    val activityRule: ActivityTestRule<TvShowDetailView> =
        object : ActivityTestRule<TvShowDetailView>(TvShowDetailView::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext : Context = InstrumentationRegistry.getInstrumentation().targetContext
                val res = Intent(targetContext,TvShowDetailView::class.java)
                res.putExtra(GlobalVal.SELECTED_TV_SHOW,tvShowDummy.id)
                return res
            }
        }

    @Test
    fun checkDataView(){
        Espresso.onView(ViewMatchers.withId(R.id.iv_poster_image)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.onView(ViewMatchers.withId(R.id.tv_tvshow_title)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.onView(ViewMatchers.withId(R.id.tv_tvshow_description)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
    }

}