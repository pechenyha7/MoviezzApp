package com.example.moviezzapp.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviezzapp.domain.exception.ApiException
import com.example.moviezzapp.domain.usecase.ResultState
import com.example.moviezzapp.domain.usecase.SearchMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

	val searchState = searchMovieUseCase.resultFlow
		.map { result ->
			when (result) {
				is ResultState.Loading -> SearchState.Loading
				is ResultState.Success -> SearchState.Success(result.content.map { it.toSearchUi() })
				is ResultState.Failure -> if (result.ex is ApiException) {
					SearchState.Failure(result.ex.message)
				} else {
					SearchState.Message(MessageType.DEFAULT_ERROR)
				}

				else -> SearchState.Message(MessageType.EMPTY_SEARCH_INPUT)
			}
		}
		.stateIn(viewModelScope, SharingStarted.Lazily, SearchState.Message(MessageType.EMPTY_SEARCH_INPUT))

	private val searchInput = MutableStateFlow("")

	init {
		viewModelScope.launch {
			searchInput.debounce(DEBOUNCE_MILLIS).distinctUntilChanged().collectLatest { input ->
				searchMovieUseCase(input)
			}
		}
	}

	fun startSearch(input: String) {
		viewModelScope.launch {
			searchInput.emit(input.trim())
		}
	}

	companion object {
		private const val DEBOUNCE_MILLIS = 400L
	}

}
