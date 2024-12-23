package com.example.moviezzapp.ui.support.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.launchRepeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
	viewLifecycleOwner.launchRepeatOnStarted(block)
}

fun LifecycleOwner.launchRepeatOnStarted(block: suspend CoroutineScope.() -> Unit) {
	launchRepeatOnLifecycle(Lifecycle.State.STARTED, block)
}

fun LifecycleOwner.launchRepeatOnLifecycle(state: Lifecycle.State, block: suspend CoroutineScope.() -> Unit) {
	this.lifecycleScope.launch {
		this@launchRepeatOnLifecycle.lifecycle.repeatOnLifecycle(state, block)
	}
}
