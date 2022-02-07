package com.ruiderson.deviget_android_test.application

import android.app.Application
import com.ruiderson.deviget_android_test.di.appModule
import com.ruiderson.deviget_android_test.di.networkModule
import com.ruiderson.deviget_android_test.extensions.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

internal class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule)
        import(networkModule)
        bind() from singleton { ViewModelFactory(this) }
    }
}
