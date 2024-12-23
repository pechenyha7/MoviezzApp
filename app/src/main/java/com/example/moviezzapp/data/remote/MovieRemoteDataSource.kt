package com.example.moviezzapp.data.remote

import com.example.moviezzapp.data.api.RemoteApiManager
import com.example.moviezzapp.domain.model.Movie
import com.example.moviezzapp.domain.model.MovieInfo
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
	private val remoteApiManager: RemoteApiManager
) {

	suspend fun getMovieDetails(id: String): Result<MovieInfo> {
		return remoteApiManager.getMovieDetails(id).map { it.toDomain() }
	}

	suspend fun searchByName(input: String): Result<List<Movie>> {
		return remoteApiManager.searchByName(input).map { result ->
			result.search?.map { it.toDomain() } ?: emptyList()
		}
	}

}
