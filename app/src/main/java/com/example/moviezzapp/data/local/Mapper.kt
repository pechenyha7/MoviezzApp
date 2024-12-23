package com.example.moviezzapp.data.local

import com.example.moviezzapp.data.database.model.FavoriteMovieDb
import com.example.moviezzapp.data.database.model.MovieInfoDb
import com.example.moviezzapp.domain.model.MovieInfo

internal fun MovieInfo.toData(): MovieInfoDb =
	MovieInfoDb(
		id = id,
		title = title,
		image = image,
		releaseYear = releaseYear,
		runtime = runtime,
		rating = rating,
		actors = actors,
		plot = plot
	)

internal fun MovieInfoDb.toDomain(): MovieInfo =
	MovieInfo(
		id = id,
		title = title,
		image = image,
		releaseYear = releaseYear,
		runtime = runtime,
		rating = rating,
		actors = actors,
		plot = plot
	)

internal fun FavoriteMovieDb.toDomain(): MovieInfo = with(movieInfo) {
	MovieInfo(
		id = id,
		title = title,
		image = image,
		releaseYear = releaseYear,
		runtime = runtime,
		rating = rating,
		actors = actors,
		plot = plot
	)
}
