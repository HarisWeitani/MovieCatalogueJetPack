package com.haris.weitani.moviecataloguejetpack.detailview

import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.EspressoIdlingResource
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.utils.FakeDummyData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MovieDetailViewTest {

    private var movieDummy = FakeDummyData.generateDummyMovies()[0]

    @get:Rule
    val activityRule: ActivityTestRule<MovieDetailView> =
        object : ActivityTestRule<MovieDetailView>(MovieDetailView::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext: Context =
                    InstrumentationRegistry.getInstrumentation().targetContext
                val result = Intent(targetContext, MovieDetailView::class.java)
                result.putExtra(GlobalVal.SELECTED_MOVIE, movieDummy.id)
                return result
            }
        }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun checkDataView() {
        Espresso.onView(ViewMatchers.withId(R.id.iv_poster_image)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        Espresso.onView(ViewMatchers.withId(R.id.tv_movie_title)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        ).check(ViewAssertions.matches(ViewMatchers.withText(movieDummy.title)))

        Espresso.onView(ViewMatchers.withId(R.id.tv_movie_description)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        ).check(ViewAssertions.matches(ViewMatchers.withText(movieDummy.overview)))
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }
}