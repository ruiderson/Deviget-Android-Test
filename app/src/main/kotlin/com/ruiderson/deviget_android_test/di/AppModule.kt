package com.ruiderson.deviget_android_test.di

import android.content.Context
import androidx.room.Room
import com.ruiderson.deviget_android_test.top_posts.data.database.RedditDatabase
import com.ruiderson.deviget_android_test.top_posts.data.network.RedditApi
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
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

    bind() from singleton {
        Room.databaseBuilder(
            instance(),
            RedditDatabase::class.java,
            RedditDatabase.DATABASE_NAME
        ).build()
    }
}
