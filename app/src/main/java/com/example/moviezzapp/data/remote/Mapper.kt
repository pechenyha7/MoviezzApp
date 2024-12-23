package com.example.moviezzapp.data.remote

import com.example.moviezzapp.data.api.model.MovieDetailsResponse
import com.example.moviezzapp.data.api.model.MovieResponse
import com.example.moviezzapp.domain.model.MovieInfo
import com.example.moviezzapp.domain.model.Movie

internal fun MovieResponse.toDomain(): Movie {
	return Movie(
		id = id,
		title = title,
		image = image,
		year = year,
		type = type,
	)
}

internal fun MovieDetailsResponse.toDomain(): MovieInfo {
	return MovieInfo(
		id = id,
		title = title,
		image = image,
		releaseYear = year,
		runtime = runtime,
		rating = rating,
		actors = actors,
		plot = plot
	)
}
