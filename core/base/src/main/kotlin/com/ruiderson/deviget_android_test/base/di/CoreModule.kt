package com.ruiderson.deviget_android_test.base.di

import com.ruiderson.deviget_android_test.base.utils.TimestampConverter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val coreModule = Kodein.Module("coreModule") {

    bind() from provider {
        TimestampConverter(
            instance()
        )
    }
}