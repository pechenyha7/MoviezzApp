package com.example.moviezzapp.data.api

import com.example.moviezzapp.data.api.model.MovieDetailsResponse
import com.example.moviezzapp.data.api.model.MovieResponse
import com.example.moviezzapp.data.api.model.SearchResponse

class RemoteApiManager(private val api: MoviesApiService)  {

	suspend fun searchByName(name: String): Result<SearchResponse> {
		return makeRequest { api.searchByName(name) }
	}

	suspend fun getMovieDetails(id: String): Result<MovieDetailsResponse> {
		return makeRequest { api.getMovieDetails(id) }
	}

}
