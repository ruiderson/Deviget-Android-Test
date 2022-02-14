package com.ruiderson.deviget_android_test.base.extensions

import androidx.annotation.MainThread
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import org.kodein.di.Kodein
import org.kodein.di.TT
import org.kodein.di.direct
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.util.LinkedList

class ViewModelFactory(
    private val injector: Kodein
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return injector.direct.Instance(TT(modelClass))
    }
}

object ViewModelProviderFactory {
    @VisibleForTesting
    var viewModel: LinkedList<ViewModel> = LinkedList()
}

@Suppress("UNCHECKED_CAST")
fun <T: ViewModel> ViewModelProvider.customGet(clazz: Class<out T>): T {
    return if (ViewModelProviderFactory.viewModel.isEmpty()) get(clazz)
    else ViewModelProviderFactory.viewModel.pop() as T
}

@MainThread
inline fun <reified VM : ViewModel, T> T.viewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this }
) : Lazy<VM> where T : KodeinAware, T : Fragment {
    val factory: ViewModelProvider.Factory by lazy {
        kodein.direct.instance()
    }

    return createViewModelLazy(
        VM::class,
        { ownerProducer().viewModelStore },
        { factory }
    )
}

@MainThread
inline fun <reified VM : ViewModel, T> T.viewModels() : Lazy<VM> where T : KodeinAware, T : FragmentActivity {
    val factory: ViewModelProvider.Factory by lazy {
        kodein.direct.instance()
    }

    return ViewModelLazy(
        VM::class,
        { viewModelStore },
        { factory }
    )
}

@MainThread
inline fun <reified VM : ViewModel, T> T.activityViewModels() : Lazy<VM> where T : KodeinAware, T : Fragment {
    val factory: ViewModelProvider.Factory by lazy {
        kodein.direct.instance()
    }

    return activityViewModels{ factory }
}
