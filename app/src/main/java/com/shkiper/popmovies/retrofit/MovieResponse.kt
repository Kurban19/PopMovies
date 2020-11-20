package com.shkiper.popmovies.retrofit

import com.google.gson.annotations.SerializedName
import com.shkiper.popmovies.models.Movie

data class MovieResponse(

    var page: Int? = null,

    var results: List<Movie>? = null,

    @SerializedName("total_results")
    var totalResults: Int? = null,

    @SerializedName("total_pages")
    var totalPages: Int? = null
)
