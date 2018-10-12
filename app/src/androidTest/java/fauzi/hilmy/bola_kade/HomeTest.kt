package fauzi.hilmy.bola_kade

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import fauzi.hilmy.bola_kade.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testPrev() {
        onView(withId(recyclerPrev))
                .check(matches(isDisplayed()))
        onView(withId(recyclerPrev)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9))
        onView(withId(recyclerPrev)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        onView(withId(floatFav))
                .check(matches(isDisplayed()))
        onView(withId(floatFav)).perform(click())
        pressBack()
        onView(withId(swipeLast)).perform(swipeUp())
    }

    @Test
    fun testNext() {
        onView(withId(bottom_nav)).check(matches(isDisplayed()))
        onView(withId(nav_next)).perform(click())
        onView(withId(recyclerNext)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(btnLineup)).perform(click())
        onView(withText("Lineups is have not been made")).check(matches(isDisplayed()))
    }

    @Test
    fun testFav() {
        onView(withId(nav_fav)).perform(click())
        onView(withId(recycler_fav))
                .check(matches(isDisplayed()))
        onView(withId(recycler_fav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(btnLineup)).perform(click())
        pressBack()
        onView(withId(floatFav)).perform(click())
//        onView(withText(R.string.removeFav)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(swipedd)).perform(swipeUp())
    }
}