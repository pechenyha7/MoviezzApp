package com.example.moviezzapp.domain.usecase

import com.example.moviezzapp.domain.model.Movie
import com.example.moviezzapp.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : FlowUseCase<String, ResultState<List<Movie>>?>() {

    override fun execute(value: String): Flow<ResultState<List<Movie>>?> = flow {
        if (value.isBlank()) {
            emit(null)
            return@flow
        }

        if (value.length < MIN_QUERY_LENGTH) {
            return@flow
        }

        emit(ResultState.Loading)
        moviesRepository.searchByName(value)
            .onSuccess { list ->
                emit(ResultState.Success(list))
            }
            .onFailure {
                it.printStackTrace()
                emit(ResultState.Failure(it))
            }
    }
        .flowOn(Dispatchers.IO)
        .catch {
            it.printStackTrace()
            emit(ResultState.Failure(it))
        }


    companion object {
        private const val MIN_QUERY_LENGTH = 3
    }

}
