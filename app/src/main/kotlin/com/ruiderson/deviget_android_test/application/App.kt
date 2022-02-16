package com.ruiderson.deviget_android_test.application

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ruiderson.deviget_android_test.di.appModule
import com.ruiderson.deviget_android_test.network.di.networkModule
import com.ruiderson.deviget_android_test.base.extensions.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal class App : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule)
        import(networkModule)
        bind<Context>() with instance(this@App.applicationContext)
        bind<ViewModelProvider.Factory>() with singleton {
            ViewModelFactory(kodein)
        }
    }
}
