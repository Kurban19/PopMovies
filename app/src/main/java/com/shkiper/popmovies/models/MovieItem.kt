package com.shkiper.popmovies.models

data class MovieItem(
    val id: String,
    val title: String,
    val image: String?,
    val rating: String?,
    val releaseDate: String?
)