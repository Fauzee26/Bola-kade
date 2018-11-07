package fauzi.hilmy.bolakade.activity


import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import fauzi.hilmy.bolakade.R
import fauzi.hilmy.bolakade.R.id.search_src_text
import fauzi.hilmy.bolakade.matches.MatchSearchActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchAndDetailTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MatchSearchActivity::class.java)

    @Test
    fun searchMatch() {

        Espresso.onView(ViewMatchers.withId(search_src_text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(search_src_text)).perform(ViewActions.typeText("mad"))

        Thread.sleep(7000)

        val recyclerView = onView(
                allOf(withId(R.id.recycleSearchMatch),
                        childAtPosition(
                                withClassName(`is`("android.widget.RelativeLayout")),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        pressBack()
        Thread.sleep(3000)
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
