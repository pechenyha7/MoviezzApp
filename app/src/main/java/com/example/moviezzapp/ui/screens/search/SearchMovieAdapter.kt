package com.example.moviezzapp.ui.screens.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil3.load
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.example.moviezzapp.R
import com.example.moviezzapp.databinding.ItemMovieBinding

class SearchMovieAdapter(
	private val clickAction: (id: String) -> Unit,
) : ListAdapter<MovieItemUi, SearchMovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
		return MovieViewHolder(
			ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
			clickAction
		)
	}

	override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	class MovieViewHolder(
		private val binding: ItemMovieBinding,
		private val clickAction: (id: String) -> Unit,
	) : ViewHolder(binding.root) {

		fun bind(item: MovieItemUi) {
			with(binding) {
				root.setOnClickListener { clickAction(item.id) }
				ivImage.load(item.imageUrl) {
					crossfade(true)
					placeholder(R.drawable.bg_placeholder)
					error(R.drawable.bg_placeholder)
				}
				tvTitle.text = item.title
				tvProductionYear.text = item.releaseYear
			}
		}
	}

	internal class MovieDiffCallback : DiffUtil.ItemCallback<MovieItemUi>() {
		override fun areItemsTheSame(oldItem: MovieItemUi, newItem: MovieItemUi): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: MovieItemUi, newItem: MovieItemUi): Boolean {
			return oldItem == newItem
		}
	}
}


