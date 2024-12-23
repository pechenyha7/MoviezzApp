package com.example.moviezzapp.data.api.providers

import com.example.moviezzapp.BuildConfig
import javax.inject.Inject

class ApiKeyProvider @Inject constructor() {
	fun provide(): String {
		return BuildConfig.API_KEY
	}
}
