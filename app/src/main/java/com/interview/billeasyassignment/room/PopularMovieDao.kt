package com.interview.billeasyassignment.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.interview.billeasyassignment.model.TopMovieDataClass


@Dao
interface PopularMovieDao {

    @Query("SELECT * FROM savePopular_movie")
    fun getAll(): LiveData<List<PopularMovieEntity>>
}