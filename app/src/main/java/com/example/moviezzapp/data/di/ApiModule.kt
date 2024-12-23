package com.example.moviezzapp.data.di

import com.example.moviezzapp.data.api.MoviesApiService
import com.example.moviezzapp.data.api.RemoteApiManager
import com.example.moviezzapp.data.api.providers.UrlProvider
import com.example.moviezzapp.data.api.providers.ApiKeyProvider
import com.example.moviezzapp.data.api.interceptor.InterceptorsProvider
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

	@Provides
	@Singleton
	fun json(): Json {
		return Json {
			isLenient = true
			ignoreUnknownKeys = true
			coerceInputValues = true
			explicitNulls = false
		}
	}

	@Provides
	@Singleton
	fun converterFactory(json: Json): Converter.Factory = json.asConverterFactory(CONTENT_TYPE_JSON.toMediaType())

	@Provides
	@Singleton
	fun interceptorsProvider(apiKeyProvider: ApiKeyProvider) = InterceptorsProvider(apiKeyProvider)

	@Provides
	@Singleton
	fun okHttpClient(
		interceptorsProvider: InterceptorsProvider
	): OkHttpClient {
		return OkHttpClient.Builder().apply {
			connectTimeout(Duration.ofSeconds(30))
			readTimeout(Duration.ofSeconds(30))
			writeTimeout(Duration.ofSeconds(30))
			interceptorsProvider.allInterceptors().forEach { addInterceptor(it) }
		}
			.build()
	}

	@Provides
	@Singleton
	fun retrofit(urlProvider: UrlProvider, client: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
		return Retrofit.Builder()
			.baseUrl(urlProvider.provideBaseUrl())
			.addConverterFactory(converterFactory)
			.client(client)
			.build()
	}

	@Provides
	@Singleton
	fun apiService(retrofit: Retrofit): MoviesApiService = retrofit.create(MoviesApiService::class.java)

	@Provides
	@Singleton
	fun apiManager(apiService: MoviesApiService): RemoteApiManager = RemoteApiManager(apiService)

	companion object {
		internal const val CONTENT_TYPE_JSON = "application/json"
	}

}

