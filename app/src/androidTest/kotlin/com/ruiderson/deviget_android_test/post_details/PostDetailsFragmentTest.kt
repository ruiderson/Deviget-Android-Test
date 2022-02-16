package com.ruiderson.deviget_android_test.post_details

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PostDetailsFragmentTest {

    @Test
    fun whenLaunchFragment_verifyAuthorIsNotVisible() = runPostDetailsFragmentTest {
        Act {
            launchFragment()
        }

        Assert {
            verifyAuthorIsNotVisible()
        }
    }

    @Test
    fun whenLaunchFragment_verifyImageIsNotVisible() = runPostDetailsFragmentTest {
        Act {
            launchFragment()
        }

        Assert {
            verifyImageIsNotVisible()
        }
    }

    @Test
    fun whenLaunchFragment_verifyTitleIsNotVisible() = runPostDetailsFragmentTest {
        Act {
            launchFragment()
        }

        Assert {
            verifyTitleIsNotVisible()
        }
    }

    @Test
    fun whenStateIsOnHidePostDetails_verifyAuthorIsNotVisible() = runPostDetailsFragmentTest {
        Arrange {
            setupOnHidePostDetailsState()
        }

        Act {
            launchFragment()
        }

        Assert {
            verifyAuthorIsNotVisible()
        }
    }

    @Test
    fun whenStateIsOnHidePostDetails_verifyTitleIsNotVisible() = runPostDetailsFragmentTest {
        Arrange {
            setupOnHidePostDetailsState()
        }

        Act {
            launchFragment()
        }

        Assert {
            verifyTitleIsNotVisible()
        }
    }

    @Test
    fun whenStateIsOnHidePostDetails_verifyImageIsNotVisible() = runPostDetailsFragmentTest {
        Arrange {
            setupOnHidePostDetailsState()
        }

        Act {
            launchFragment()
        }

        Assert {
            verifyImageIsNotVisible()
        }
    }

    @Test
    fun whenStateIsOnShowPostDetails_verifyAuthorIsCorrect() = runPostDetailsFragmentTest {
        Arrange {
            setupOnShowPostDetailsState()
        }

        Act {
            launchFragment()
        }

        Assert {
            verifyAuthorIsCorrect()
        }
    }

    @Test
    fun whenStateIsOnShowPostDetails_verifyTitleIsCorrect() = runPostDetailsFragmentTest {
        Arrange {
            setupOnShowPostDetailsState()
        }

        Act {
            launchFragment()
        }

        Assert {
            verifyTitleIsCorrect()
        }
    }

    @Test
    fun givenValidThumbnail_whenStateIsOnShowPostDetails_verifyImageIsVisible() = runPostDetailsFragmentTest {
        Arrange {
            setupOnShowPostDetailsState()
        }

        Act {
            launchFragment()
        }

        Assert {
            verifyImageIsVisible()
        }
    }

    @Test
    fun givenInvalidThumbnail_whenStateIsOnShowPostDetails_verifyImageIsNotVisible() = runPostDetailsFragmentTest {
        Arrange {
            setupInvalidThumbnailPost()
        }

        Act {
            launchFragment()
        }

        Assert {
            verifyImageIsNotVisible()
        }
    }

    private fun runPostDetailsFragmentTest(
        block: PostDetailsFragmentRobot.() -> Unit
    ) = runBlockingTest {
        PostDetailsFragmentRobot(block)
    }
}
