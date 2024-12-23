package com.example.moviezzapp.ui.screens.search

import androidx.annotation.StringRes
import com.example.moviezzapp.R
import com.example.moviezzapp.domain.model.Movie

sealed interface SearchState {
	data object Loading : SearchState
	data class Success(val content: List<MovieItemUi>) : SearchState
	data class Failure(val message: String?) : SearchState
	data class Message(val messageType: MessageType) : SearchState
}

enum class MessageType(@StringRes val resId: Int) {
	DEFAULT_ERROR(R.string.error_message),
	EMPTY_SEARCH_INPUT(R.string.empty_movies)
}

data class MovieItemUi(
	val id: String,
	val imageUrl: String,
	val title: String,
	val releaseYear: String
)

internal fun Movie.toSearchUi(): MovieItemUi =
	MovieItemUi(
		id = id,
		imageUrl = image,
		title = title,
		releaseYear = year,
	)
