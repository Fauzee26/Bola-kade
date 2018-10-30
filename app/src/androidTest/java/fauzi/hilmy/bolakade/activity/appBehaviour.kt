package fauzi.hilmy.bolakade.activity


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import fauzi.hilmy.bolakade.R
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
class appBehaviour {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun appBehaviour() {
        val bottomNavigationItemView = onView(
                allOf(withId(R.id.nav_prev), withContentDescription("Prev Match"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                0),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        val recyclerView = onView(
                allOf(withId(R.id.recyclerPrev),
                        childAtPosition(
                                withClassName(`is`("android.widget.RelativeLayout")),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(3, click()))

        val appCompatButton = onView(
                allOf(withId(R.id.btnLineup), withText("L i n e u p s"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewww),
                                        0),
                                6),
                        isDisplayed()))
        appCompatButton.perform(click())

        pressBack()

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

        pressBack()

        val bottomNavigationItemView2 = onView(
                allOf(withId(R.id.nav_next), withContentDescription("Next Match"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        val recyclerView2 = onView(
                allOf(withId(R.id.recyclerNext),
                        childAtPosition(
                                withClassName(`is`("android.widget.RelativeLayout")),
                                0)))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(10, click()))
        val appCompatButton2 = onView(
                allOf(withId(R.id.btnLineup), withText("L i n e u p s"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.viewww),
                                        0),
                                6),
                        isDisplayed()))
        appCompatButton2.perform(click())

        pressBack()

        val bottomNavigationItemView3 = onView(
                allOf(withId(R.id.nav_fav), withContentDescription("Favorite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.bottom_nav),
                                        0),
                                2),
                        isDisplayed()))
        bottomNavigationItemView3.perform(click())

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
