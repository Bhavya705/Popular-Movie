package com.interview.billeasyassignment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.billeasyassignment.model.TopMovieDataClass
import com.interview.billeasyassignment.repository.HomeRepository
import com.interview.billeasyassignment.room.PopularMovieDatabase
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class HomeViewModel(private val popularMovieDatabase: PopularMovieDatabase,context: Context): ViewModel() {

    var homeRepository: HomeRepository = HomeRepository(context)

    private val _topMovieListLiveData = MutableLiveData<TopMovieDataClass>()
    private val topMovieListLiveData: LiveData<TopMovieDataClass>
        get() = _topMovieListLiveData

     fun getTopMovieList(api: String): LiveData<TopMovieDataClass>{
        // Launches a coroutine having viewModel scope
        viewModelScope.launch {

            val response = homeRepository.getTopMovie(api)

            if (response.isSuccessful) {

                popularMovieDatabase.popularMovieDao().getAll()
                val imagesList: TopMovieDataClass? = response.body()
                _topMovieListLiveData.value = imagesList!!

            } else {
                Timber.e("Error fetching images")
            }
        }
        return topMovieListLiveData
    }
}