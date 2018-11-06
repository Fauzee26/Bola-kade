package fauzi.hilmy.bolakade.activity


import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import fauzi.hilmy.bolakade.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AppBehaviourTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun appBehaviourTest() {
        val appCompatSpinner = onView(
                allOf(withId(R.id.spinLeague),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()))
        appCompatSpinner.perform(click())
        pressBack()

        val recyclerView = onView(
                allOf(withId(R.id.recyclerPrev),
                        childAtPosition(
                                withClassName(`is`("android.widget.RelativeLayout")),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        val floatingActionButton = onView(
                allOf(withId(R.id.floatFav),
                        childAtPosition(
                                allOf(withId(R.id.viewww),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()))
        floatingActionButton.perform(click())

        val appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageButton.perform(click())

        val bottomNavigationItemView = onView(
                allOf(withId(R.id.nav_team), withContentDescription("Teams"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        val recyclerView2 = onView(
                allOf(withId(R.id.recyclerTeam),
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                1)))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val tabView = onView(
                allOf(withContentDescription("Player"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.detail_tab),
                                        0),
                                1),
                        isDisplayed()))
        tabView.perform(click())

        val viewPager = onView(
                allOf(withId(R.id.detail_viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.linearMain),
                                        0),
                                2),
                        isDisplayed()))
        viewPager.perform(swipeLeft())

        val recyclerView3 = onView(
                allOf(withId(R.id.recyclerPlayer),
                        childAtPosition(
                                withClassName(`is`("android.widget.RelativeLayout")),
                                0)))
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        pressBack()

        pressBack()

        val bottomNavigationItemView2 = onView(
                allOf(withId(R.id.nav_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        val tabView2 = onView(
                allOf(withContentDescription("TEAM"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.favTab),
                                        0),
                                1),
                        isDisplayed()))
        tabView2.perform(click())

        val viewPager2 = onView(
                allOf(withId(R.id.favViewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.framee),
                                        0),
                                1),
                        isDisplayed()))
        viewPager2.perform(swipeLeft())

        val bottomNavigationItemView3 = onView(
                allOf(withId(R.id.nav_match), withContentDescription("Matches"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                0),
                        isDisplayed()))
        bottomNavigationItemView3.perform(click())

        val actionMenuItemView = onView(
                allOf(withId(R.id.action_searchh), withContentDescription("Search"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView.perform(click())

        val searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()))
        searchAutoComplete.perform(replaceText("bar"), closeSoftKeyboard())

        val recyclerView4 = onView(
                allOf(withId(R.id.recycleSearchMatch),
                        childAtPosition(
                                withClassName(`is`("android.widget.RelativeLayout")),
                                0)))
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        pressBack()

        pressBack()

        val bottomNavigationItemView4 = onView(
                allOf(withId(R.id.nav_team), withContentDescription("Teams"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView4.perform(click())

        val appCompatImageView = onView(
                allOf(withId(R.id.search_button), withContentDescription("Search"),
                        childAtPosition(
                                allOf(withId(R.id.search_bar),
                                        childAtPosition(
                                                withId(R.id.action_search),
                                                0)),
                                1),
                        isDisplayed()))
        appCompatImageView.perform(click())

        val searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text),
                        childAtPosition(
                                allOf(withId(R.id.search_plate),
                                        childAtPosition(
                                                withId(R.id.search_edit_frame),
                                                1)),
                                0),
                        isDisplayed()))
        searchAutoComplete2.perform(replaceText("pal"), closeSoftKeyboard())

        val recyclerView5 = onView(
                allOf(withId(R.id.recyclerTeam),
                        childAtPosition(
                                withClassName(`is`("android.widget.LinearLayout")),
                                1)))
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val actionMenuItemView2 = onView(
                allOf(withId(R.id.addMenu), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()))
        actionMenuItemView2.perform(click())

        pressBack()

        val bottomNavigationItemView5 = onView(
                allOf(withId(R.id.nav_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView5.perform(click())

        val tabView3 = onView(
                allOf(withContentDescription("TEAM"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.favTab),
                                        0),
                                1),
                        isDisplayed()))
        tabView3.perform(click())

        val viewPager3 = onView(
                allOf(withId(R.id.favViewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.framee),
                                        0),
                                1),
                        isDisplayed()))
        viewPager3.perform(swipeLeft())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

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
