package com.example.moviezzapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviezzapp.data.database.model.MovieInfoDb.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MovieInfoDb(
	@PrimaryKey
	val id: String,
	val title: String,
	val image: String,
	val releaseYear: String,
	val runtime: String,
	val rating: String,
	val actors: String,
	val plot: String
) {

	companion object {
		const val TABLE_NAME = "MovieInfoDb"
	}
}

