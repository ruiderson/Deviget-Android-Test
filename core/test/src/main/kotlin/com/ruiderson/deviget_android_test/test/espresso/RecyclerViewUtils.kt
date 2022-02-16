package com.ruiderson.deviget_android_test.test.espresso

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import org.junit.Assert.assertEquals
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher

class RecyclerViewItemCountAssertion(
    private val expectedCount: Int
) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val itemCount = (view as RecyclerView).adapter?.itemCount ?: 0
        assertEquals(itemCount, expectedCount)
    }
}

fun itemAtPosition(
    position: Int, itemMatcher: Matcher<View?>,
    targetViewId: Int
): Matcher<View?> {
    return object : BoundedMatcher<View?, RecyclerView>(
        RecyclerView::class.java
    ) {
        override fun describeTo(description: Description) {
            description.appendText("has view id $itemMatcher at position $position")
        }

        override fun matchesSafely(recyclerView: RecyclerView): Boolean {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
            val targetView = viewHolder!!.itemView.findViewById<View>(targetViewId)
            return itemMatcher.matches(targetView)
        }
    }
}

class actionOnItemView(
    private val viewId: Int
) : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isEnabled()
    }

    override fun getDescription(): String {
        return "Click on specific view"
    }

    override fun perform(uiController: UiController?, view: View) {
        val itemView: View = view.findViewById(viewId)
        itemView.performClick()
    }
}