package com.example.moviezzapp.data.api.interceptor

import com.example.moviezzapp.data.api.providers.ApiKeyProvider
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor(
	private val apiKeyProvider: ApiKeyProvider
) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()

		val key = apiKeyProvider.provide()
		val newUrl = request.url.newBuilder()
			.addQueryParameter(API_KEY, key)
			.build()

		return chain.proceed(
			request.newBuilder().url(newUrl).build()
		)
	}

	companion object {
		private const val API_KEY = "apikey"
	}
}
