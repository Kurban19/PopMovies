package com.shkiper.popmovies.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkiper.popmovies.models.Movie
import com.shkiper.popmovies.retrofit.ApiHelper
import com.shkiper.popmovies.util.Resource
import kotlinx.coroutines.launch

class DescriptionViewModel(private val apiHelper: ApiHelper, private val movieId: String): ViewModel()  {

    private val movie = MutableLiveData<Resource<Movie>>()

    init {
        fetchMovie()
    }

    private fun fetchMovie(){
        viewModelScope.launch {
            movie.postValue(Resource.loading(null))
            try {
                val movieFromApi = apiHelper.findById(movieId = movieId, language = "ru")
                movie.postValue(Resource.success(movieFromApi.results?.get(0)))
            } catch (e: Exception) {
                movie.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getMovie(): MutableLiveData<Resource<Movie>> {
        return movie
    }

}