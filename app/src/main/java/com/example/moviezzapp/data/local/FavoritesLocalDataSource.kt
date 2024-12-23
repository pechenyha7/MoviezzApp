package com.example.moviezzapp.data.local

import com.example.moviezzapp.data.database.dao.FavoritesDao
import com.example.moviezzapp.data.database.model.FavoriteDb
import com.example.moviezzapp.domain.model.MovieInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesLocalDataSource(private val favoritesDao: FavoritesDao) {

	suspend fun addToFavorites(movieId: String) {
		favoritesDao.insertFavorite(FavoriteDb(movieId = movieId))
	}

	suspend fun removeFromFavorite(movieId: String) {
		favoritesDao.removeFavorite(movieId)
	}

	suspend fun hasFavorite(movieId: String): Boolean {
		return favoritesDao.getFavorite(movieId) != null
	}

	fun observeFavorite(movieId: String): Flow<Boolean> {
		return favoritesDao.observeFavorite(movieId).map { it != null }
	}

	fun observeFavoriteMovies(): Flow<List<MovieInfo>> {
		return favoritesDao.observeFavoriteMovies().map { list ->
			list.map { it.toDomain() }
		}
	}
}
