package com.shkiper.popmovies.viewmodels

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkiper.popmovies.models.Movie
import com.shkiper.popmovies.retrofit.ApiHelper
import com.shkiper.popmovies.util.Resource
import kotlinx.coroutines.launch

class SearchViewModel(private val apiHelper: ApiHelper): ViewModel() {


    private val movies = MutableLiveData<Resource<List<Movie>>>()


    fun findMovies(query: String){
        viewModelScope.launch {
            movies.postValue(Resource.loading(null))
            try {
                val moviesFromApi = apiHelper.searchMovies(searchQuery = query, language = "ru")
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