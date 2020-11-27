package com.shkiper.popmovies.retrofit

import com.shkiper.popmovies.util.AppConstants
import retrofit2.Call

interface ApiHelper {

    suspend fun getPopularMovies(apiKey: String = AppConstants.API_KEY, language: String, page: String = "1"): MovieResponse

    suspend fun searchMovies(apiKey: String = AppConstants.API_KEY, language: String, searchQuery: String): MovieResponse

    suspend fun findById(apiKey: String = AppConstants.API_KEY, language: String, movieId: String): MovieResponse

}