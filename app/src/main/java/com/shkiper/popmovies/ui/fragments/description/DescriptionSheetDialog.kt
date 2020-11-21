package com.shkiper.popmovies.ui.fragments.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shkiper.popmovies.R


class DescriptionSheetDialog : BottomSheetDialogFragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.description_bottom_sheet, container, false)


        return v
    }


}