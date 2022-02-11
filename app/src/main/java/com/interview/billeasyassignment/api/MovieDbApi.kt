package com.interview.billeasyassignment.api

import com.interview.billeasyassignment.model.TopMovieDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbApi {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("api_key") api: String): Response<TopMovieDataClass>
}