package com.example.moviezzapp.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
	@SerialName("imdbID")
	val id: String,
	@SerialName("Title")
	val title: String,
	@SerialName("Poster")
	val image: String,
	@SerialName("Year")
	val year: String,
	@SerialName("Type")
	val type: String
)
