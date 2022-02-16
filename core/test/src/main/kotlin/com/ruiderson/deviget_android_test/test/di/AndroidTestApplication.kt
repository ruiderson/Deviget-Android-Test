package com.ruiderson.deviget_android_test.test.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider
import org.kodein.di.Kodein
import org.kodein.di.conf.KodeinGlobalAware
import org.kodein.di.conf.global
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class AndroidTestApplication : Application(), KodeinGlobalAware {

    override fun onCreate() {
        super.onCreate()
        setupKodein()
    }
}

private fun setupKodein() {
    Kodein.global.mutable = true
    Kodein.global.addConfig {
        bind<Context>() with singleton {
            ApplicationProvider.getApplicationContext()
        }
    }
    Kodein.global.addConfig {
        bind<Application>("app") with singleton {
            ApplicationProvider.getApplicationContext()
        }
    }
    Kodein.global.addConfig {
        bind<ViewModelProvider.Factory>() with singleton {
            ViewModelTestFactory(kodein)
        }
    }
}

fun resetKodein() {
    Kodein.global.clear()
    setupKodein()
}

fun mockKodeinModule(
    vararg modules: Kodein.Module,
    config: Kodein.MainBuilder.() -> Unit = {}
) {
    resetKodein()
    modules.forEach {
        Kodein.global.addImport(it, true)
    }
    Kodein.global.addConfig(config)
}
