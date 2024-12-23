package com.example.moviezzapp.data.api.providers

import com.example.moviezzapp.BuildConfig
import javax.inject.Inject

class UrlProvider @Inject constructor() {
	fun provideBaseUrl(): String {
		return BuildConfig.BASE_URL
	}
}
