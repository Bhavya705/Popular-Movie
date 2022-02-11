package com.interview.billeasyassignment.viewmodel

import androidx.lifecycle.*
import com.interview.billeasyassignment.model.TopMovieDataClass
import com.interview.billeasyassignment.repository.HomeRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(): ViewModel() {

    //val readAllData: LiveData<List<PopularMovieEntity>> = homeRepository.readAllData
    private val homeRepository= HomeRepository()
    private val _topMovieListLiveData = MutableLiveData<TopMovieDataClass>()
    private val topMovieListLiveData: LiveData<TopMovieDataClass>
        get() = _topMovieListLiveData


     fun getTopMovieList(api: String): LiveData<TopMovieDataClass>{
        // Launches a coroutine having viewModel scope
        viewModelScope.launch {

            val response = homeRepository.getTopMovie(api)

            if (response.isSuccessful) {
                //popularMovieDatabase.popularMovieDao().getAll()
                val imagesList: TopMovieDataClass? = response.body()
                _topMovieListLiveData.value = imagesList!!

            } else {
                Timber.e("Error fetching images")
            }
        }
        return topMovieListLiveData
    }
}

//class MovieViewModelFactory(private val repository: HomeRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return HomeViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

