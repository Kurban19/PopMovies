package com.shkiper.popmovies.retrofit


import com.shkiper.popmovies.util.AppConstants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String,
        @Query("page") page: String = "1"
    ): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String,
        @Query("query") searchQuery: String
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun findById(
            @Path("movie_id") movieId : String,
            @Query("api_key") apiKey: String = API_KEY,
            @Query("language") language: String
    ): MovieResponse


}