package com.shkiper.popmovies.repositories

import androidx.lifecycle.MutableLiveData
import com.shkiper.popmovies.models.Movie
import ru.skillbranch.devintensive.extensions.mutableLiveData

object MovieRepository {

    private const val TAG = "MovieRepository"


    private var movies: MutableLiveData<List<Movie>> = mutableLiveData(listOf())


    fun find(movieId: String): Movie {
        val ind = movies.value!!.indexOfFirst { it.id == movieId}
        return movies.value!![ind]
    }

    fun setMovies(source: List<Movie>){
        movies = mutableLiveData(source)
    }



}