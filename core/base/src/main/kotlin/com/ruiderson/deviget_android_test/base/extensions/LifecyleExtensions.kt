package com.ruiderson.deviget_android_test.base.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.uiLifecycleScope(block: suspend CoroutineScope.() -> Unit) = lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED, block)
}
