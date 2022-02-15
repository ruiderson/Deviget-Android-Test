package com.ruiderson.deviget_android_test.test.espresso

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import org.junit.Assert.assertEquals

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