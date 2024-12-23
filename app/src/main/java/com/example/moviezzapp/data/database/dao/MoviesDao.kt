package com.example.moviezzapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviezzapp.data.database.model.MovieInfoDb

@Dao
abstract class MoviesDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract suspend fun save(item: MovieInfoDb)

	@Query("SELECT * FROM MovieInfoDb WHERE id = :id")
	abstract suspend fun getMovieById(id: String): MovieInfoDb?

}
