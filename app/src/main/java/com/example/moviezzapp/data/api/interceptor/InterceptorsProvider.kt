package com.example.moviezzapp.data.api.interceptor

import com.example.moviezzapp.data.api.providers.ApiKeyProvider
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class InterceptorsProvider(
	private val apiKeyProvider: ApiKeyProvider
) {

	fun allInterceptors(): List<Interceptor> = buildList {
		add(httpInterceptor())
		add(apikeyInterceptor(apiKeyProvider))
	}

	private fun httpInterceptor(): Interceptor =
		HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

	private fun apikeyInterceptor(apiKeyProvider: ApiKeyProvider): Interceptor =
		ApiKeyInterceptor(apiKeyProvider)
}
