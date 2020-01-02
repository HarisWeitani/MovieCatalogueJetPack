package com.haris.weitani.moviecataloguejetpack.mainactivity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.RecyclerViewItemCountAssertion
import com.haris.weitani.moviecataloguejetpack.common.MovieListFragment
import com.haris.weitani.moviecataloguejetpack.testing.SingleFragmentActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieListFragmentTest {

    @get:Rule
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(
        SingleFragmentActivity::class.java
    )
    private val movieFragment= MovieListFragment()

    @Before
    fun setUp() {
        activityRule.activity.setFragment(movieFragment)
    }

    @Test
    fun getData() {
        onView(withId(R.id.rv_movie_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).check(RecyclerViewItemCountAssertion(10))
    }
}