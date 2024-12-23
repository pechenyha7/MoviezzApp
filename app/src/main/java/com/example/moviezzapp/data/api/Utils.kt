package com.example.moviezzapp.data.api

import com.example.moviezzapp.domain.exception.ApiException

interface ApiResponse {
	val isSuccessful: Boolean
	val errorMessage: String?
}

internal suspend fun <T> makeRequest(request: suspend () -> T): Result<T> {
	return try {
		when (val result = request()) {
			is ApiResponse -> result.toResult()
			else -> Result.success(request())
		}
	} catch (throwable: Throwable) {
		Result.failure(throwable)
	}
}

private fun <T : ApiResponse> T.toResult(): Result<T> {
	return if (isSuccessful) {
		Result.success(this)
	} else {
		val errorMessage = errorMessage ?: "Unknown error"
		Result.failure(ApiException(errorMessage))
	}
}
