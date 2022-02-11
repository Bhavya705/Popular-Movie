package com.interview.billeasyassignment.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="savePopular_movie")
data class PopularMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val movieImage: String,
    val movieName: String,
    val movieRating: Double,
    val releaseDate: String
)