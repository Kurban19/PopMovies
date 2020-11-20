package com.shkiper.popmovies.models

import com.google.gson.annotations.SerializedName


data class Movie(

    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val image: String?,
    @SerializedName("vote_average")
    val rating: String?,
    @SerializedName("overview")
    val description: String?,
    @SerializedName("release_date")
    val releaseDate: String?
) {

    fun toMovieItem(): MovieItem{
        return MovieItem(id, title, image, rating, releaseDate)
    }


}