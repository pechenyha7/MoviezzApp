package com.example.moviezzapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.moviezzapp.data.database.model.FavoriteDb
import com.example.moviezzapp.data.database.model.FavoriteMovieDb
import com.example.moviezzapp.data.database.model.MovieInfoDb
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoritesDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract suspend fun insertFavorite(favoriteDb: FavoriteDb)

	@Query("DELETE FROM FavoriteDb WHERE movieId = :movieId")
	abstract suspend fun removeFavorite(movieId: String)

	@Transaction
	@Query("""
        SELECT * FROM FavoriteDb
        INNER JOIN MovieInfoDb ON FavoriteDb.movieId = MovieInfoDb.id
		ORDER BY FavoriteDb.id DESC
    """)
	abstract fun observeFavoriteMovies(): Flow<List<FavoriteMovieDb>>

	@Query("SELECT * FROM FavoriteDb WHERE movieId = :movieId")
	abstract suspend fun getFavorite(movieId: String): FavoriteDb?

	@Query("SELECT * FROM FavoriteDb WHERE movieId = :movieId")
	abstract fun observeFavorite(movieId: String): Flow<FavoriteDb?>

}
