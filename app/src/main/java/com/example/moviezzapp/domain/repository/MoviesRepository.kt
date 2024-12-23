package com.example.moviezzapp.domain.repository

import com.example.moviezzapp.domain.model.Movie
import com.example.moviezzapp.domain.model.MovieInfo

interface MoviesRepository {

	suspend fun searchByName(query: String): Result<List<Movie>>

	suspend fun getMovieDetails(id: String): Result<MovieInfo>

}
