package com.example.moviezzapp.ui.support

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class ProminentLayoutManager(
	context: Context,
	@RecyclerView.Orientation orientation: Int,
	private val minScaleDistanceFactor: Float = 1.5f,
	private val scaleDownBy: Float = 0.5f
) : LinearLayoutManager(context, orientation, false) {

	override fun onLayoutCompleted(state: RecyclerView.State?) =
		super.onLayoutCompleted(state).also { scaleChildren() }

	override fun scrollHorizontallyBy(
		dx: Int,
		recycler: RecyclerView.Recycler,
		state: RecyclerView.State
	) = super.scrollHorizontallyBy(dx, recycler, state).also {
		scaleChildren()
	}

	override fun scrollVerticallyBy(
		dy: Int,
		recycler: RecyclerView.Recycler,
		state: RecyclerView.State
	): Int = super.scrollVerticallyBy(dy, recycler, state).also {
		scaleChildren()
	}

	private fun scaleChildren() {
		if (orientation == VERTICAL) {
			val containerCenter = height / 2f

			// Any view further than this threshold will be fully scaled down
			val scaleDistanceThreshold = minScaleDistanceFactor * containerCenter

			for (i in 0 until childCount) {
				val child = getChildAt(i)!!

				val childCenter = (child.top + child.bottom) / 2f
				val distanceToCenter = abs(childCenter - containerCenter)

				val scaleDownAmount = (distanceToCenter / scaleDistanceThreshold).coerceAtMost(1f)
				val scale = 1f - scaleDownBy * scaleDownAmount

				child.scaleX = scale
				child.scaleY = scale

				val translationDirection = if (childCenter > containerCenter) -1 else 1
				val translationYFromScale = translationDirection * child.height * (1 - scale) / 2f
				child.translationY = translationYFromScale
			}
		} else {
			val containerCenter = width / 2f

			// Any view further than this threshold will be fully scaled down
			val scaleDistanceThreshold = minScaleDistanceFactor * containerCenter

			for (i in 0 until childCount) {
				val child = getChildAt(i)!!

				val childCenter = (child.left + child.right) / 2f
				val distanceToCenter = abs(childCenter - containerCenter)

				val scaleDownAmount = (distanceToCenter / scaleDistanceThreshold).coerceAtMost(1f)
				val scale = 1f - scaleDownBy * scaleDownAmount

				child.scaleX = scale
				child.scaleY = scale

				val translationDirection = if (childCenter > containerCenter) -1 else 1
				val translationXFromScale = translationDirection * child.width * (1 - scale) / 2f
				child.translationX = translationXFromScale
			}
		}
	}
}
