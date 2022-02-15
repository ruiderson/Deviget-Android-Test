package com.ruiderson.deviget_android_test.top_posts

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TopPostsFragmentTest {

    private val idlingResource = CountingIdlingResource(this.javaClass.simpleName)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun finish() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }


    @Test
    fun whenLaunchFragment_verifyItemListCountIsCorrect() = runTopPostsFragmentTest {
        launchFragment()

        verifyItemListCountIsCorrect()
    }

    private fun runTopPostsFragmentTest(
        block: TopPostsFragmentRobot.() -> Unit
    ) = runBlockingTest {
        TopPostsFragmentRobot(idlingResource, block)
    }
}
