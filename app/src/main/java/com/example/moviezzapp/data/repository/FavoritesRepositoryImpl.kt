package com.example.moviezzapp.data.repository

import com.example.moviezzapp.data.local.FavoritesLocalDataSource
import com.example.moviezzapp.domain.model.MovieInfo
import com.example.moviezzapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class FavoritesRepositoryImpl(
	private val favoritesLocalDataSource: FavoritesLocalDataSource
) : FavoritesRepository {

	override suspend fun toggleFavorites(movieId: String) {
		if (favoritesLocalDataSource.hasFavorite(movieId)) {
			favoritesLocalDataSource.removeFromFavorite(movieId)
		} else {
			favoritesLocalDataSource.addToFavorites(movieId)
		}
	}

	override fun observeIsFavorite(movieId: String): Flow<Boolean> {
		return favoritesLocalDataSource.observeFavorite(movieId)
	}

	override fun observeFavorites(): Flow<List<MovieInfo>> {
		return favoritesLocalDataSource.observeFavoriteMovies()
	}

}
