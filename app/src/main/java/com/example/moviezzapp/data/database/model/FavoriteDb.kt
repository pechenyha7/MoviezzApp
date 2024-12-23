package com.example.moviezzapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviezzapp.data.database.model.FavoriteDb.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FavoriteDb(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val movieId: String
) {

	companion object {
		const val TABLE_NAME = "FavoriteDb"
	}
}
