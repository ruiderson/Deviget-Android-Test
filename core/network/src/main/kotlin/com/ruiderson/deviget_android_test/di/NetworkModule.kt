package com.ruiderson.deviget_android_test.di

import com.ruiderson.deviget_android_test.core.HttpClientFactory
import com.ruiderson.deviget_android_test.reddit.RedditClient
import com.ruiderson.deviget_android_test.reddit.RedditClientImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val networkModule = Kodein.Module("networkModule") {

    bind<HttpClientFactory>() with provider {
        HttpClientFactory()
    }

    bind<RedditClient>() with singleton {
        RedditClientImpl(
            instance()
        )
    }
}
