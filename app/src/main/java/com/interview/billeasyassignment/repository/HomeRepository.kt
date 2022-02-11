package com.interview.billeasyassignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.interview.billeasyassignment.api.MovieDbApi
import com.interview.billeasyassignment.model.TopMovieDataClass
import com.interview.billeasyassignment.room.PopularMovieDao
import com.interview.billeasyassignment.room.PopularMovieDatabase
import com.interview.billeasyassignment.room.PopularMovieEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeRepository() {

   // val readAllData: LiveData<List<PopularMovieEntity>> = popularMovieDao.getAll()

    suspend fun getTopMovie(api: String): Response<TopMovieDataClass> {

        // Create OkHttp Client
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val topMovieListService = retrofit.create(MovieDbApi::class.java)

        return topMovieListService.getTopRatedMovie(api)
    }

}