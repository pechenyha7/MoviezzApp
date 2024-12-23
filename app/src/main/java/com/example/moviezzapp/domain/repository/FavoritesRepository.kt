package com.example.moviezzapp.domain.repository

import com.example.moviezzapp.domain.model.MovieInfo
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository  {

	suspend fun toggleFavorites(movieId: String)

	fun observeIsFavorite(movieId: String): Flow<Boolean>

	fun observeFavorites(): Flow<List<MovieInfo>>

}
