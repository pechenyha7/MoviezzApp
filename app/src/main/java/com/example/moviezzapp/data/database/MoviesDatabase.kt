package com.example.moviezzapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviezzapp.data.database.dao.FavoritesDao
import com.example.moviezzapp.data.database.dao.MoviesDao
import com.example.moviezzapp.data.database.model.FavoriteDb
import com.example.moviezzapp.data.database.model.MovieInfoDb

@Database(
	version = 1,
	entities = [
		MovieInfoDb::class,
		FavoriteDb::class
	],
	exportSchema = false
)
abstract class MoviesDatabase : RoomDatabase() {

	abstract fun moviesDao(): MoviesDao
	abstract fun favoritesDao(): FavoritesDao

	companion object {

		private const val DATABASE_NAME = "MoviesDatabase"

		fun getDatabase(application: Application): MoviesDatabase {
			return Room.databaseBuilder(
				application,
				MoviesDatabase::class.java,
				DATABASE_NAME
			)
				.enableMultiInstanceInvalidation()
				.build()
		}
	}
}
