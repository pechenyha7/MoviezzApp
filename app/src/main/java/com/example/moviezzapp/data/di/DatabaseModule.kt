package com.example.moviezzapp.data.di

import android.app.Application
import com.example.moviezzapp.data.database.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

	@Provides
	@Singleton
	fun database(application: Application): MoviesDatabase = MoviesDatabase.getDatabase(application)

}
