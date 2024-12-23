package com.example.moviezzapp.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviezzapp.domain.repository.FavoritesRepository
import com.example.moviezzapp.domain.repository.MoviesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModel.Factory::class)
class DetailsViewModel @AssistedInject constructor(
	@Assisted args: DetailsArgs,
	private val moviesRepository: MoviesRepository,
	private val favoritesRepository: FavoritesRepository
) : ViewModel() {

	@AssistedFactory
	interface Factory {
		fun create(args: DetailsArgs): DetailsViewModel
	}

	private val _detailsState = MutableStateFlow<DetailsState>(DetailsState.Loading)
	val detailsState = _detailsState.asStateFlow()

	private val _favoriteState = MutableStateFlow<FavoriteState?>(null)
	val favoriteState = _favoriteState.asStateFlow().filterNotNull()

	private val movieId = MutableStateFlow<String?>(null)

	init {
		viewModelScope.launch(Dispatchers.IO) {
			movieId.filterNotNull().flatMapLatest { id ->
				favoritesRepository.observeIsFavorite(id)
			}.map { isFavorite ->
				if (isFavorite) FavoriteState.FAVORITE else FavoriteState.NOT_FAVORITE
			}.collect { state ->
				_favoriteState.value = state
			}
		}
		viewModelScope.launch(Dispatchers.IO) {
			fetchMovieDetails(args.id)
		}
	}

	fun updateFavorite() {
		viewModelScope.launch(Dispatchers.IO) {
			val id = movieId.value ?: return@launch
			favoritesRepository.toggleFavorites(id)
		}
	}

	private fun fetchMovieDetails(id: String) {
		viewModelScope.launch(Dispatchers.IO) {
			moviesRepository.getMovieDetails(id)
				.onSuccess { item ->
					movieId.emit(item.id)
					_detailsState.emit(DetailsState.Success(item.toDetailsUi()))
				}
				.onFailure {
					_detailsState.emit(DetailsState.Failure(it))
					it.printStackTrace()
				}
		}
	}

}
