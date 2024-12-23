package com.example.moviezzapp.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviezzapp.domain.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
	favoritesRepository: FavoritesRepository
) : ViewModel() {

	val favorites = favoritesRepository.observeFavorites()
		.map { list -> list.map { it.toFavoriteUi() } }
		.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

}
