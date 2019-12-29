package com.haris.weitani.moviecataloguejetpack.main_activity

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.haris.weitani.moviecataloguejetpack.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun movieFragmenTest(){
        val linearLayout = onView(
            Matchers.allOf(
                childAtPosition(
                    Matchers.allOf(
                        withId(R.id.rv_movie_list),
                        childAtPosition(
                            withClassName(Matchers.`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        onView(withId(R.id.iv_poster_image)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_movie_description)).check(ViewAssertions.matches(isDisplayed()))


        Espresso.pressBack()

        val tabView = onView(
            Matchers.allOf(
                withContentDescription("TV Show"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tabs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val linearLayout2 = onView(
            Matchers.allOf(
                childAtPosition(
                    Matchers.allOf(
                        withId(R.id.rv_tvshow_list),
                        childAtPosition(
                            withClassName(Matchers.`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout2.perform(click())

        onView(withId(R.id.iv_poster_image)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_title)).check(ViewAssertions.matches((isDisplayed())))
        onView(withId(R.id.tv_tvshow_description)).check(ViewAssertions.matches((isDisplayed())))

        Espresso.pressBack()
    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}