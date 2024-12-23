package com.example.moviezzapp.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class FavoriteMovieDb(
	@Embedded
	val favoriteDb: FavoriteDb,

	@Relation(
		parentColumn = "movieId",
		entityColumn = "id"
	)
	val movieInfo: MovieInfoDb
)
