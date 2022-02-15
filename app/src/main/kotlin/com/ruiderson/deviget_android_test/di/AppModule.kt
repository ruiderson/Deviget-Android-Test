package com.ruiderson.deviget_android_test.di

import android.content.Context
import androidx.room.Room
import com.ruiderson.deviget_android_test.top_posts.adapter.RedditPostAdapter
import com.ruiderson.deviget_android_test.top_posts.adapter.RedditPostAdapterImpl
import com.ruiderson.deviget_android_test.top_posts.data.RedditRepository
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditDatabase
import com.ruiderson.deviget_android_test.top_posts.data.datasource.RedditDataSourceRepository
import com.ruiderson.deviget_android_test.top_posts.data.datasource.RedditPagingSourceFactory
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditApi
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditPostDtoConverter
import com.ruiderson.deviget_android_test.top_posts.domain.TopPostsUseCase
import com.ruiderson.deviget_android_test.top_posts.domain.TopPostsViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val appModule = Kodein.Module("appModule") {

    bind() from provider {
        instance<Context>().resources
    }

    bind() from provider {
        RedditApi(
            instance()
        )
    }

    bind() from provider {
        RedditPostDtoConverter()
    }

    bind() from singleton {
        Room.databaseBuilder(
            instance(),
            RedditDatabase::class.java,
            RedditDatabase.DATABASE_NAME
        ).build()
    }

    bind() from provider {
        RedditDataSourceRepository(
            instance(),
            instance(),
            instance()
        )
    }

    bind() from provider {
        RedditPagingSourceFactory(
            instance(),
            instance()
        )
    }

    bind() from provider {
        RedditRepository(
            instance(),
            instance()
        )
    }

    bind() from provider {
        TopPostsUseCase(
            instance()
        )
    }

    bind() from provider {
        TopPostsViewModel(
            instance()
        )
    }

    bind<RedditPostAdapter>() with provider {
        RedditPostAdapterImpl()
    }
}
