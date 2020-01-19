package com.haris.weitani.moviecataloguejetpack.favoriteactivity


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.RecyclerViewItemCountAssertion
import com.haris.weitani.moviecataloguejetpack.common.EspressoIdlingResource
import com.haris.weitani.moviecataloguejetpack.mainactivity.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FavoriteActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun favoriteActivityTest() {
        val linearLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rv_movie_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
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
        onView(withId(R.id.iv_favorite)).check(ViewAssertions.matches(isDisplayed()))

        val appCompatImageView = onView(
            allOf(
                withId(R.id.iv_favorite),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        appCompatImageView.perform(scrollTo(), click())

        pressBack()

        val linearLayout12 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rv_movie_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        linearLayout12.perform(click())

        onView(withId(R.id.iv_poster_image)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_movie_description)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.iv_favorite)).check(ViewAssertions.matches(isDisplayed()))

        val imageView12 = onView(
            allOf(
                withId(R.id.iv_favorite),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        imageView12.perform(scrollTo(), click())

        pressBack()

        val tabView = onView(
            allOf(
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
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rv_tvshow_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
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
        onView(withId(R.id.iv_favorite)).check(ViewAssertions.matches(isDisplayed()))

        val appCompatImageView2 = onView(
            allOf(
                withId(R.id.iv_favorite),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        appCompatImageView2.perform(scrollTo(), click())

        pressBack()

        val actionMenuItemView = onView(
            allOf(
                withId(R.id.menu_favorite), withContentDescription("My Favorite"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        onView(withId(R.id.rv_movie_list)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movie_list)).check(RecyclerViewItemCountAssertion(2))

        val linearLayout3 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rv_movie_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout3.perform(click())

        onView(withId(R.id.iv_poster_image)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_movie_title)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_movie_description)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.iv_favorite)).check(ViewAssertions.matches(isDisplayed()))

        pressBack()

        val tabView2 = onView(
            allOf(
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
        tabView2.perform(click())

        onView(withId(R.id.rv_tvshow_list)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow_list)).check(RecyclerViewItemCountAssertion(1))

        val linearLayout4 = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.rv_tvshow_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.FrameLayout")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout4.perform(click())

        onView(withId(R.id.iv_poster_image)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_tvshow_title)).check(ViewAssertions.matches((isDisplayed())))
        onView(withId(R.id.tv_tvshow_description)).check(ViewAssertions.matches((isDisplayed())))
        onView(withId(R.id.iv_favorite)).check(ViewAssertions.matches(isDisplayed()))

        pressBack()
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
