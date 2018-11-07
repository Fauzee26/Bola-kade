package fauzi.hilmy.bolakade.activity


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import fauzi.hilmy.bolakade.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TabAndBottomNavigationTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun tabAndBottomNavigationTest() {
        val bottomNavigationItemView = onView(
                allOf(withId(R.id.nav_match), withContentDescription("Matches"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                0),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        val bottomNavigationItemView2 = onView(
                allOf(withId(R.id.nav_team), withContentDescription("Teams"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        val bottomNavigationItemView3 = onView(
                allOf(withId(R.id.nav_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView3.perform(click())

        val tabView = onView(
                allOf(withContentDescription("TEAM"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.favTab),
                                        0),
                                1),
                        isDisplayed()))
        tabView.perform(click())

        val viewPager = onView(
                allOf(withId(R.id.favViewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.framee),
                                        0),
                                1),
                        isDisplayed()))
        viewPager.perform(swipeLeft())

        val bottomNavigationItemView4 = onView(
                allOf(withId(R.id.nav_match), withContentDescription("Matches"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                0),
                        isDisplayed()))
        bottomNavigationItemView4.perform(click())

        val tabView2 = onView(
                allOf(withContentDescription("NEXT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.match_tab),
                                        0),
                                1),
                        isDisplayed()))
        tabView2.perform(click())

        val viewPager2 = onView(
                allOf(withId(R.id.match_viewpager),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.framee),
                                        0),
                                1),
                        isDisplayed()))
        viewPager2.perform(swipeLeft())
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
