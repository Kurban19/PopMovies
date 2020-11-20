package com.shkiper.popmovies.retrofit

class ApiHelperImpl(private val apiService: MovieApiService): ApiHelper {
    override suspend fun getPopularMovies(
        apiKey: String,
        language: String,
        page: String
    ) = apiService.getPopularMovies(apiKey, language, page)

    override suspend fun searchMovies(
        apiKey: String,
        language: String,
        searchQuery: String
    ) = apiService.searchMovies(apiKey, language, searchQuery)

}