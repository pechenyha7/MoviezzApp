package com.example.moviezzapp.data.local

import com.example.moviezzapp.data.database.dao.MoviesDao
import com.example.moviezzapp.domain.model.MovieInfo

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

	suspend fun saveMovie(info: MovieInfo) {
		moviesDao.save(info.toData())
	}

	suspend fun getMovie(id: String): MovieInfo? {
		return moviesDao.getMovieById(id)?.toDomain()
	}
}
