package com.example.moviezzapp.ui.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.moviezzapp.R
import com.example.moviezzapp.ui.screens.ScreensNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

	@ActivityScoped
	@Provides
	fun screensNavigator(navController: NavController): ScreensNavigator =
		ScreensNavigator(navController)

	@ActivityScoped
	@Provides
	fun navController(fragmentManager: FragmentManager): NavController =
		(fragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment).navController

	@ActivityScoped
	@Provides
	fun appCompatActivity(activity: Activity): AppCompatActivity = activity as AppCompatActivity

	@ActivityScoped
	@Provides
	fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

}
