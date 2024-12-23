package com.example.moviezzapp.data.di

import com.example.moviezzapp.data.database.MoviesDatabase
import com.example.moviezzapp.data.local.FavoritesLocalDataSource
import com.example.moviezzapp.data.local.MoviesLocalDataSource
import com.example.moviezzapp.data.remote.MovieRemoteDataSource
import com.example.moviezzapp.data.repository.FavoritesRepositoryImpl
import com.example.moviezzapp.data.repository.MoviesRepositoryImpl
import com.example.moviezzapp.domain.repository.FavoritesRepository
import com.example.moviezzapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

	@Provides
	@Singleton
	fun moviesLocalDataSource(database: MoviesDatabase): MoviesLocalDataSource =
		MoviesLocalDataSource(database.moviesDao())

	@Provides
	@Singleton
	fun favoritesLocalDataSource(database: MoviesDatabase): FavoritesLocalDataSource =
		FavoritesLocalDataSource(database.favoritesDao())

	@Provides
	@Singleton
	fun movieRepository(
		remoteDataSource: MovieRemoteDataSource,
		localDataSource: MoviesLocalDataSource
	): MoviesRepository = MoviesRepositoryImpl(remoteDataSource, localDataSource)

	@Provides
	@Singleton
	fun favoritesRepository(
		localDataSource: FavoritesLocalDataSource,
	): FavoritesRepository = FavoritesRepositoryImpl(localDataSource)
}
