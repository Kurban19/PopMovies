package com.shkiper.popmovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkiper.popmovies.models.Movie
import com.shkiper.popmovies.retrofit.ApiHelper
import com.shkiper.popmovies.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val apiHelper: ApiHelper): ViewModel() {

    private val movies = MutableLiveData<Resource<List<Movie>>>()

    companion object{
        const val TAG = "MainViewModel"
    }

    init {
        fetchMovies()
    }

    private fun fetchMovies(){
        viewModelScope.launch {
            movies.postValue(Resource.loading(null))
            try {
                val moviesFromApi = apiHelper.getPopularMovies(language = "ru")
                movies.postValue(Resource.success(moviesFromApi.results))
            } catch (e: Exception) {
                movies.postValue(Resource.error(e.toString(), null))
            }
        }
    }


    fun searchMovies(query: String){
        viewModelScope.launch {
            movies.postValue(Resource.loading(null))
            try {
                val moviesFromApi = apiHelper.searchMovies(language = "ru", searchQuery = query)
                movies.postValue(Resource.success(moviesFromApi.results))
            } catch (e: Exception) {
                movies.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getMovies(): LiveData<Resource<List<Movie>>>? {
        return movies
    }

}