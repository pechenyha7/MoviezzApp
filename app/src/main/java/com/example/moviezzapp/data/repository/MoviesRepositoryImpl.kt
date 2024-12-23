package com.example.moviezzapp.data.repository

import com.example.moviezzapp.data.local.MoviesLocalDataSource
import com.example.moviezzapp.data.remote.MovieRemoteDataSource
import com.example.moviezzapp.domain.model.Movie
import com.example.moviezzapp.domain.model.MovieInfo
import com.example.moviezzapp.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl(
	private val remoteDataSource: MovieRemoteDataSource,
	private val localDataSource: MoviesLocalDataSource,
) : MoviesRepository {

	override suspend fun searchByName(query: String): Result<List<Movie>> {
		return remoteDataSource.searchByName(query)
	}

	override suspend fun getMovieDetails(id: String): Result<MovieInfo> {
		val localInfo = localDataSource.getMovie(id)

		if (localInfo != null) {
			return Result.success(localInfo)
		}

		return remoteDataSource.getMovieDetails(id).onSuccess {
			localDataSource.saveMovie(it)
		}
	}

}
