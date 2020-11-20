package com.shkiper.popmovies.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shkiper.popmovies.retrofit.ApiHelper
import com.shkiper.popmovies.viewmodels.MainViewModel
import com.shkiper.popmovies.viewmodels.SearchViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper) as T
        }

        if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
            return SearchViewModel(apiHelper) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}