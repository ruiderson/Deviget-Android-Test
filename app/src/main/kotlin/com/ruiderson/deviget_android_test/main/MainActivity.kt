package com.ruiderson.deviget_android_test.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ruiderson.deviget_android_test.base.extensions.uiLifecycleScope
import com.ruiderson.deviget_android_test.base.extensions.viewBinding
import com.ruiderson.deviget_android_test.base.extensions.viewModels
import com.ruiderson.deviget_android_test.databinding.ActivityMainBinding
import com.ruiderson.deviget_android_test.shared.domain.SharedRedditPostState
import com.ruiderson.deviget_android_test.shared.domain.SharedRedditPostViewModel
import kotlinx.coroutines.flow.collect
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

internal class MainActivity : AppCompatActivity(),
    KodeinAware
{

    override val kodein: Kodein by kodein()

    private val sharedViewModel: SharedRedditPostViewModel by viewModels()

    private val viewBinding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() = with(viewBinding) {
        mainSlidingPane.close()
    }

    private fun setupObservers() = uiLifecycleScope {
        sharedViewModel.redditPostState.collect {
            onStateChange(it)
        }
    }

    private fun onStateChange(state: SharedRedditPostState) = when(state) {
        is SharedRedditPostState.OnShowPostDetails -> showPostDetails()
        is SharedRedditPostState.OnHidePostDetails -> Unit
    }

    private fun showPostDetails() = with(viewBinding) {
        mainSlidingPane.open()
    }

    override fun onBackPressed() {
        with(viewBinding.mainSlidingPane) {
            when {
                !this.isSlideable -> super.onBackPressed()
                this.isOpen -> close()
                else -> super.onBackPressed()
            }
        }
    }
}
