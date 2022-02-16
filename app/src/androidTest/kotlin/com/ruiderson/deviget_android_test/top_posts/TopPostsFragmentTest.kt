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
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun whenLaunchFragment_verifyGetRedditTopPostsIsCalled() = runTopPostsFragmentTest {
        Act {
            launchFragment()
        }

        Assert {
            verifyGetRedditTopPostsIsCalled()
        }
    }

    @Test
    fun whenLaunchFragment_verifyAdapterSubmitDataIsCalled() = runTopPostsFragmentTest {
        Act {
            launchFragment()
        }

        Assert {
            verifyAdapterSubmitDataIsCalled()
        }
    }

    @Test
    fun whenLaunchFragment_verifyItemCountIsCorrect() = runTopPostsFragmentTest {
        Act {
            launchFragment()
        }

        Assert {
            verifyItemCountIsCorrect()
        }
    }

    @Test
    fun whenPerformSwipeRefresh_verifyShouldRefreshIsCalled() = runTopPostsFragmentTest {
        Act {
            launchFragment()
            performSwipeRefresh()
        }

        Assert {
            verifyShouldRefreshIsCalled()
        }
    }

    @Test
    fun whenPerformSwipeRefresh_verifyAdapterRefreshIsCalled() = runTopPostsFragmentTest {
        Act {
            launchFragment()
            performSwipeRefresh()
        }

        Assert {
            verifyAdapterRefreshIsCalled()
        }
    }

    @Test
    fun whenPerformDismissAllClick_verifyDismissAllIsCalled() = runTopPostsFragmentTest {
        Act {
            launchFragment()
            performDismissAllClick()
        }

        Assert {
            verifyDismissAllIsCalled()
        }
    }

    @Test
    fun whenLaunchFragment_verifyItemIsUnread() = runTopPostsFragmentTest {
        val itemPosition = 5

        Act {
            launchFragment()
            performScrollAtPosition(itemPosition)
        }

        Assert {
            verifyItemIsUnread(itemPosition)
        }
    }

    @Test
    fun whenLaunchFragment_verifyItemAuthorIsCorrect() = runTopPostsFragmentTest {
        val itemPosition = 5

        Act {
            launchFragment()
            performScrollAtPosition(itemPosition)
        }

        Assert {
            verifyItemAuthorIsCorrect(itemPosition)
        }
    }

    @Test
    fun whenLaunchFragment_verifyItemEntryDateIsCorrect() = runTopPostsFragmentTest {
        val itemPosition = 5

        Act {
            launchFragment()
            performScrollAtPosition(itemPosition)
        }

        Assert {
            verifyItemEntryDateIsCorrect(itemPosition)
        }
    }

    @Test
    fun givenValidItemThumbnail_whenLaunchFragment_verifyItemThumbnailIsDisplayed() = runTopPostsFragmentTest {
        val itemPosition = 0

        Arrange {
            insertValidItemThubmnail(itemPosition)
        }

        Act {
            launchFragment()
            performScrollAtPosition(itemPosition)
        }

        Assert {
            verifyItemThumbnailIsDisplayed(itemPosition)
        }
    }

    @Test
    fun givenInvalidItemThumbnail_whenLaunchFragment_verifyItemThumbnailIsNotDisplayed() = runTopPostsFragmentTest {
        val itemPosition = 5

        Act {
            launchFragment()
            performScrollAtPosition(itemPosition)
        }

        Assert {
            verifyItemThumbnailIsNotDisplayed(itemPosition)
        }
    }

    @Test
    fun whenLaunchFragment_verifyItemTitleIsCorrect() = runTopPostsFragmentTest {
        val itemPosition = 5

        Act {
            launchFragment()
            performScrollAtPosition(itemPosition)
        }

        Assert {
            verifyItemTitleIsCorrect(itemPosition)
        }
    }

    @Test
    fun whenLaunchFragment_verifyItemCommentsIsCorrect() = runTopPostsFragmentTest {
        val itemPosition = 5

        Act {
            launchFragment()
            performScrollAtPosition(itemPosition)
        }

        Assert {
            verifyItemCommentsIsCorrect(itemPosition)
        }
    }

    @Test
    fun whenItemDismissButtonClicked_verifyItemDismissIsCalled() = runTopPostsFragmentTest {
        val itemPosition = 5

        Act {
            launchFragment()
            performScrollAtPosition(itemPosition)
            performItemDismissButtonClick(itemPosition)
        }

        Assert {
            verifyItemDismissIsCalled()
        }
    }

    @Test
    fun whenItemClicked_verifyMarkRedditPostAsReadIsCalled() = runTopPostsFragmentTest {
        val itemPosition = 5

        Act {
            launchFragment()
            performScrollAtPosition(itemPosition)
            performClickAtPosition(itemPosition)
        }

        Assert {
            verifyMarkRedditPostAsReadIsCalled()
        }
    }

    private fun runTopPostsFragmentTest(
        block: TopPostsFragmentRobot.() -> Unit
    ) = runBlockingTest {
        TopPostsFragmentRobot(idlingResource, block)
    }
}