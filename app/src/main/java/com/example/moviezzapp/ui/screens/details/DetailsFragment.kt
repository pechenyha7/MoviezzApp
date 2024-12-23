package com.example.moviezzapp.ui.screens.details

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.example.moviezzapp.R
import com.example.moviezzapp.databinding.FragmentMovieDetailsBinding
import com.example.moviezzapp.ui.support.ext.launchRepeatOnStarted
import com.example.moviezzapp.ui.screens.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.withCreationCallback
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : BaseFragment(R.layout.fragment_movie_details) {

	private val binding by viewBinding(FragmentMovieDetailsBinding::bind)
	private val viewModel by viewModels<DetailsViewModel>(
		extrasProducer = {
			defaultViewModelCreationExtras.withCreationCallback<DetailsViewModel.Factory> { factory ->
				factory.create(DetailsArgs(requireArguments().getString(ARG_ID)!!))
			}
		}
	)

	override fun setupViews() {
		with(binding) {
			lytAppBar.setNavigationOnClickListener { screensNavigator.navigateUp() }
			ivFavorite.setOnClickListener { viewModel.updateFavorite() }
		}
	}

	override fun setupData() {
		launchRepeatOnStarted {
			launch {
				viewModel.detailsState.collect { state ->
					when (state) {
						is DetailsState.Loading -> {
							updateContentVisibility(isLoading = true)
						}

						is DetailsState.Failure -> {
							updateContentVisibility(hasError = true)
						}

						is DetailsState.Success -> {
							updateContentVisibility(hasContent = true)
							updateContent(state.content)
						}
					}
				}
			}
			launch {
				viewModel.favoriteState.collectLatest { state ->
					binding.ivFavorite.setImageResource(state.imageRes)
				}
			}
		}
	}

	private fun updateContentVisibility(
		isLoading: Boolean = false,
		hasContent: Boolean = false,
		hasError: Boolean = false
	) {
		with(binding) {
			vProgress.isVisible = isLoading
			lytContent.isVisible = hasContent
			tvError.isVisible = hasError
		}
	}

	private fun updateContent(content: DetailsUi) {
		with(binding) {
			with(content) {
				ivMovie.load(imageUrl) {
					crossfade(true)
					placeholder(R.drawable.bg_placeholder)
					error(R.drawable.bg_placeholder)
				}
				tvTitle.text = title
				tvProductionYear.text = productionYear
				tvDuration.text = runtime
				tvRating.text = getString(R.string.details_rating_template, rating)
				tvActors.text = actors
				tvPlot.text = plot
			}
		}
	}

	companion object {
		private const val ARG_ID = "ID"

		fun buildArgs(id: String): Bundle {
			return bundleOf(ARG_ID to id)
		}
	}
}
