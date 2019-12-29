package com.haris.weitani.moviecataloguejetpack.main_activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.RecyclerViewItemCountAssertion
import com.haris.weitani.moviecataloguejetpack.common.TvShowListFragment
import com.haris.weitani.moviecataloguejetpack.testing.SingleFragmentActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowListFragmentTest{

    @get:Rule
    val activityRule: ActivityTestRule<SingleFragmentActivity> = ActivityTestRule(
        SingleFragmentActivity::class.java
    )
    private val tvShowFragment = TvShowListFragment()


    @Before
    fun setUp() {
        activityRule.activity.setFragment(tvShowFragment)
    }

    @Test
    fun getData() {
        onView(withId(R.id.rv_tvshow_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow_list)).check(RecyclerViewItemCountAssertion(10))
    }
}