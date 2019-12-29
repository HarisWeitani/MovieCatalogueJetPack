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
import org.junit.Rule
import org.junit.Test


class MovieDetailViewTest {

    private var movieDummy = FakeDummyData.generateDummyMovies()[1]

    @get:Rule
    val activityRule: ActivityTestRule<MovieDetailView> =
        object : ActivityTestRule<MovieDetailView>(MovieDetailView::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext: Context =
                    InstrumentationRegistry.getInstrumentation().getTargetContext()
                val result = Intent(targetContext, MovieDetailView::class.java)
                result.putExtra(GlobalVal.SELECTED_MOVIE, movieDummy.id)
                return result
            }
        }

    @Test
    fun checkDataView() {
        Espresso.onView(ViewMatchers.withId(R.id.iv_poster_image)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.onView(ViewMatchers.withId(R.id.tv_movie_title)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        ).check(ViewAssertions.matches(ViewMatchers.withText(movieDummy.name)))

        Espresso.onView(ViewMatchers.withId(R.id.tv_movie_description)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        ).check(ViewAssertions.matches(ViewMatchers.withText(movieDummy.desc)))
    }
}