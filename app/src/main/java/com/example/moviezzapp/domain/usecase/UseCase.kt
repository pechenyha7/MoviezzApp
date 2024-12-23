package com.example.moviezzapp.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

/**
 * Simple use case exposing result as a flow.
 * Result flow will emit null while the action has not been triggered
 */
@ExperimentalCoroutinesApi
abstract class FlowUseCase<K, T> {

	private val _trigger = MutableStateFlow<K?>(null)

	/**
	 * Exposes result of this use case
	 */
	val resultFlow: Flow<T?> = _trigger.flatMapLatest { trigger ->
		trigger?.let { execute(it) } ?: flowOf(null)
	}

	/**
	 * Triggers the execution of this use case
	 */
	suspend operator fun invoke(value: K) {
		_trigger.emit(value)
	}

	protected abstract fun execute(value: K) : Flow<T>
}

sealed interface ResultState<out T> {
	data class Success<T>(val content: T) : ResultState<T>
	data class Failure(val ex: Throwable) : ResultState<Nothing>
	data object Loading : ResultState<Nothing>
}
