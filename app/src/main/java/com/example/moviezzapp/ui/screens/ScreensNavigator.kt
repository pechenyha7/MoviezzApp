package com.example.moviezzapp.ui.screens

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

class ScreensNavigator(private val navController: NavController) {

	fun navigateUp(): Boolean {
		return navController.navigateUp()
	}

	fun navigate(@IdRes resId: Int, bundle: Bundle? = null) {
		navController.navigate(resId, bundle)
	}

}
