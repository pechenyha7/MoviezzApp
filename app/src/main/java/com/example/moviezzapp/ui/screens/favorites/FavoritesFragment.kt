package com.example.moviezzapp.ui.screens.favorites

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviezzapp.R
import com.example.moviezzapp.databinding.FragmentFavoritesBinding
import com.example.moviezzapp.ui.support.ext.launchRepeatOnStarted
import com.example.moviezzapp.ui.screens.BaseFragment
import com.example.moviezzapp.ui.screens.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : BaseFragment(R.layout.fragment_favorites) {

	private val binding by viewBinding(FragmentFavoritesBinding::bind)
	private val viewModel by viewModels<FavoritesViewModel>()

	private val adapter by lazy { FavoriteMoviesAdapter(::onItemClicked) }

	override fun setupViews() {
		with(binding) {
			lytAppBar.setNavigationOnClickListener { screensNavigator.navigateUp() }
			rvFavorites.adapter = adapter
		}
	}

	override fun setupData() {
		launchRepeatOnStarted {
			launch {
				viewModel.favorites.collect { items ->
					binding.tvMessage.isVisible = items.isEmpty()
					adapter.submitList(items)
				}
			}
		}
	}

	private fun onItemClicked(id: String) {
		screensNavigator.navigate(
			R.id.action_favoritesFragment_to_detailsFragment,
			DetailsFragment.buildArgs(id)
		)
	}

}
