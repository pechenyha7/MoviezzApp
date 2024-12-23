package com.example.moviezzapp.ui.support.ext

import android.app.Activity
import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat.getInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard(fromView: View? = null, currentWindow: Window = requireActivity().window) {
	requireActivity().hideKeyboard(fromView, currentWindow)
}

fun Activity.hideKeyboard(forView: View? = null, currentWindow: Window = this.window) {
	(forView ?: this.currentFocus ?: currentWindow.decorView).let { view ->
		getInsetsController(currentWindow, view).hide(WindowInsetsCompat.Type.ime())
	}
	(forView ?: currentFocus)?.clearFocus()
}
