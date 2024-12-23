package com.example.moviezzapp.ui.screens

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

	@Inject
	protected lateinit var screensNavigator: ScreensNavigator

	protected abstract fun setupViews()

	protected abstract fun setupData()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupViews()
		setupData()
	}

}
