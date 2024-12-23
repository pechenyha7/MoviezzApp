package com.example.moviezzapp.ui.screens.search

import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviezzapp.R
import com.example.moviezzapp.databinding.FragmentSearchBinding
import com.example.moviezzapp.ui.screens.BaseFragment
import com.example.moviezzapp.ui.screens.details.DetailsFragment
import com.example.moviezzapp.ui.support.ProminentLayoutManager
import com.example.moviezzapp.ui.support.ext.hideKeyboard
import com.example.moviezzapp.ui.support.ext.launchRepeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search) {

	private val binding by viewBinding(FragmentSearchBinding::bind)
	private val viewModel by viewModels<SearchViewModel>()

	private val movieAdapter by lazy { SearchMovieAdapter(::onItemClicked) }

	override fun setupViews() {
		with(binding) {
			ivFavorites.setOnClickListener { openFavorites() }

			rvMovies.apply {
				adapter = movieAdapter
				layoutManager = ProminentLayoutManager(requireContext(), minScaleDistanceFactor = 5f)
				PagerSnapHelper().attachToRecyclerView(rvMovies)
			}

			etSearch.apply {
				doOnTextChanged { text, _, _, _ ->
					viewModel.startSearch(text?.toString() ?: "")
				}
				onFocusChangeListener = OnFocusChangeListener { v, focused ->
					if (!focused) hideKeyboard(v)
				}
				setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
					if (actionId == EditorInfo.IME_ACTION_DONE) {
						etSearch.clearFocus()
						return@OnEditorActionListener true
					}
					false
				})
			}
		}
	}

	override fun setupData() {
		launchRepeatOnStarted {
			launch {
				viewModel.searchState.collect { state ->
					when (state) {
						is SearchState.Message -> {
							updateContentVisibility(hasMessage = true, message = getString(state.messageType.resId))
						}

						is SearchState.Failure -> {
							updateContentVisibility(hasMessage = true, message = state.message)
						}

						SearchState.Loading -> {
							updateContentVisibility(isLoading = true, hasContent = true)
						}

						is SearchState.Success -> {
							updateContentVisibility(hasContent = true)
							movieAdapter.submitList(state.content)
						}
					}
				}
			}
		}
	}

	private fun updateContentVisibility(
		isLoading: Boolean = false,
		hasMessage: Boolean = false,
		hasContent: Boolean = false,
		message: String? = null
	) {
		with(binding) {
			vProgress.isVisible = isLoading
			tvMessage.isVisible = hasMessage
			tvMessage.text = message
			if (!hasContent) movieAdapter.submitList(emptyList())
		}
	}

	private fun onItemClicked(id: String) {
		screensNavigator.navigate(
			R.id.action_searchFragment_to_detailsFragment,
			DetailsFragment.buildArgs(id)
		)
	}

	private fun openFavorites() {
		screensNavigator.navigate(R.id.action_searchFragment_to_favoritesFragment)
	}

}
