package com.example.moviezzapp.ui.screens.details

import androidx.annotation.DrawableRes
import com.example.moviezzapp.R
import com.example.moviezzapp.domain.model.MovieInfo

sealed interface DetailsState {
	data class Success(val content: DetailsUi) : DetailsState
	data class Failure(val ex: Throwable) : DetailsState
	data object Loading : DetailsState
}

data class DetailsUi(
	val imageUrl: String,
	val title: String,
	val productionYear: String,
	val runtime: String,
	val rating: String,
	val actors: String,
	val plot: String
)

enum class FavoriteState(@DrawableRes val imageRes: Int) {
	FAVORITE(R.drawable.ic_favorites_fill),
	NOT_FAVORITE(R.drawable.ic_favorites_stroke)
}

internal fun MovieInfo.toDetailsUi(): DetailsUi =
	DetailsUi(
		imageUrl = image,
		title = title,
		productionYear = releaseYear,
		runtime = runtime,
		rating = rating,
		actors = actors,
		plot = plot
	)
