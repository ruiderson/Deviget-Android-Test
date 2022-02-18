package com.ruiderson.deviget_android_test.test.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.TT
import org.kodein.di.direct

class ViewModelTestFactory(
    private val injector: Kodein
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return injector.direct.Instance(TT(modelClass))
    }
}
