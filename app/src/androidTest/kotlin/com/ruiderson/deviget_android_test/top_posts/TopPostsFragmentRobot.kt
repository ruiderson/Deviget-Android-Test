package com.ruiderson.deviget_android_test.top_posts

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.ruiderson.deviget_android_test.R
import com.ruiderson.deviget_android_test.di.appModule
import com.ruiderson.deviget_android_test.test.di.mockKodeinModule
import com.ruiderson.deviget_android_test.test.espresso.RecyclerViewItemCountAssertion
import com.ruiderson.deviget_android_test.top_posts.adapter.IdlingResourceRedditPostAdapter
import com.ruiderson.deviget_android_test.top_posts.adapter.RedditPostAdapter
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditDatabase
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditPostDao
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditApi
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditPostDto
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditTopPostsDto
import io.mockk.*
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class TopPostsFragmentRobot(
    idlingResource: CountingIdlingResource,
    block: TopPostsFragmentRobot.() -> Unit
) {

    private val postAdapter = IdlingResourceRedditPostAdapter(idlingResource)

    private val api: RedditApi = mockk {
        coEvery { getRedditTopPosts(any(), any()) } returns mockRedditTopPostsDto()
    }

    private val dataBase: RedditDatabase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        RedditDatabase::class.java
    ).build()

    private val redditPostDao: RedditPostDao = spyk(
        dataBase.getRedditPostDao()
    )

    private val spyDataBase: RedditDatabase = spyk(dataBase) {
        every { getRedditPostDao() } returns redditPostDao
    }

    private lateinit var fragmentScenario: FragmentScenario<TopPostsFragment>

    init {
        setup()
        block.invoke(this)
    }

    private fun setup() {
        mockKodeinModule(appModule) {
            bind<RedditPostAdapter>(overrides = true) with provider { postAdapter }
            bind(overrides = true) from provider { api }
            bind(overrides = true) from provider { spyDataBase }
        }
    }

    fun verifyItemListCountIsCorrect() {
        onView(withId(R.id.topEntriesRecyclerView))
            .check(RecyclerViewItemCountAssertion(ITEM_COUNT))
    }

    fun launchFragment() {
        fragmentScenario = launchFragmentInContainer(
            themeResId = R.style.Theme_AppTheme
        )
    }

    private fun mockRedditTopPostsDto(): RedditTopPostsDto = mockk {
        every { data.after } returns "123a"
        every { getPosts() } returns mutableListOf<RedditPostDto>().apply {
            for (i in 0 until ITEM_COUNT) {
                add(RedditPostDto(
                        id = i.toString(),
                        title = "title $i",
                        author = "author $i",
                        num_comments = i.toString(),
                        thumbnail = "thum $i",
                        created_utc = "create $i"
                ))
            }
        }
    }

    companion object {
        private const val ITEM_COUNT = 10
    }
}
