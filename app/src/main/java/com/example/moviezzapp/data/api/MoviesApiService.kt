package com.example.moviezzapp.data.api

import com.example.moviezzapp.data.api.model.MovieDetailsResponse
import com.example.moviezzapp.data.api.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

	@GET("?")
	suspend fun searchByName(@Query("s") name: String): SearchResponse

	@GET("?")
	suspend fun getMovieDetails(@Query("i") id: String): MovieDetailsResponse

}
