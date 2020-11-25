package com.shkiper.popmovies.ui.fragments.description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shkiper.popmovies.R
import com.shkiper.popmovies.repositories.MovieRepository
import com.shkiper.popmovies.util.AppConstants
import kotlinx.android.synthetic.main.description_bottom_sheet.*


class DescriptionSheetDialog : BottomSheetDialogFragment(){

    companion object{
        fun getNewInstance(args: Bundle): DescriptionSheetDialog {
            val sheetDialog = DescriptionSheetDialog()
            sheetDialog.arguments = args
            return sheetDialog
        }
    }

    private val TAG = "SheetDialog"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.description_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

    }

    private fun initViews(){
        val movie = MovieRepository.find(arguments?.getString(AppConstants.MOVIE_ID) ?: "something went wrong")

        if(movie.image != null){
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w185${movie.image}")
                    .into(iv_movie_image)
        }else {
            Glide.with(this).clear(iv_movie_image)
        }

        tv_movie_title_description.text = movie.title
        tv_movie_date_description.text = movie.releaseDate
        tv_movie_rating_description.text = movie.rating
        tv_movie_description.text = movie.description

    }


}