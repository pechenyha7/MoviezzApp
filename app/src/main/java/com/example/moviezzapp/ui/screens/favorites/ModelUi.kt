package com.example.moviezzapp.ui.screens.favorites

import com.example.moviezzapp.domain.model.MovieInfo

data class FavoriteItemUi(
	val id: String,
	val imageUrl: String,
	val title: String,
	val releaseYear: String
)

internal fun MovieInfo.toFavoriteUi(): FavoriteItemUi =
	FavoriteItemUi(
		id = id,
		imageUrl = image,
		title = title,
		releaseYear = releaseYear
	)
