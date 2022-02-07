package com.ruiderson.deviget_android_test.extensions

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.DKodein
import org.kodein.di.generic.instanceOrNull
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.on

class ViewModelFactory(private val injector: DKodein) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return injector.instanceOrNull<ViewModel>(tag = modelClass.simpleName) as T?
            ?: modelClass.newInstance()
    }
}

inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : KodeinAware, T : FragmentActivity {
    return viewModels(factoryProducer = { direct.instance() })
}

inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : KodeinAware, T : Fragment {
    return viewModels(factoryProducer = { getFactoryInstance() })
}

inline fun <reified VM : ViewModel> Kodein.Builder.viewModel(overrides: Boolean? = null): Kodein.Builder.TypeBinder<VM> {
    return bind<VM>(VM::class.java.simpleName, overrides)
}

fun <T> T.getFactoryInstance(
): ViewModelProvider.Factory where T : KodeinAware, T : Fragment {
    val viewModeFactory: ViewModelProvider.Factory by kodein.on(activity).instance()
    return viewModeFactory
}
