package com.example.moviezzapp.data.api.model

import com.example.moviezzapp.data.api.ApiResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
	@SerialName("imdbID")
	val id: String,
	@SerialName("Title")
	val title: String,
	@SerialName("Poster")
	val image: String,
	@SerialName("Year")
	val year: String,
	@SerialName("Runtime")
	val runtime: String,
	@SerialName("imdbRating")
	val rating: String,
	@SerialName("Actors")
	val actors: String,
	@SerialName("Plot")
	val plot: String,

	@SerialName("Response")
	override val isSuccessful: Boolean,
	@SerialName("Error")
	override val errorMessage: String?
): ApiResponse
