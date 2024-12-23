package com.example.moviezzapp.data.api.model

import com.example.moviezzapp.data.api.ApiResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
	@SerialName("Search")
	val search: List<MovieResponse>?,
	val totalResults: String?,
	@SerialName("Response")
	override val isSuccessful: Boolean,
	@SerialName("Error")
	override val errorMessage: String?
) : ApiResponse
