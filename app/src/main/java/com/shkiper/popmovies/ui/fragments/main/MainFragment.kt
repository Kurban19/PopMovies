package com.shkiper.popmovies.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.shkiper.popmovies.R
import com.shkiper.popmovies.models.MovieItem
import com.shkiper.popmovies.retrofit.ApiHelperImpl
import com.shkiper.popmovies.retrofit.RetrofitBuilder
import com.shkiper.popmovies.ui.adapters.MoviesAdapter
import com.shkiper.popmovies.ui.custom.SpacingItemDecorator
import com.shkiper.popmovies.ui.fragments.description.DescriptionSheetDialog
import com.shkiper.popmovies.ui.fragments.search.SearchFragment
import com.shkiper.popmovies.util.Status
import com.shkiper.popmovies.util.ViewModelFactory
import com.shkiper.popmovies.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var movieAdapter: MoviesAdapter
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initViews()
        setupObserver()
    }

    private fun initViews(){
        movieAdapter = MoviesAdapter{
//            val bundle = Bundle()
//            bundle.putString(AppConstants.MOVIE_ID, it.id)
//            val fragment = DescriptionFragment()
//            fragment.arguments = bundle
//
//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.mainLayout, fragment)
//                .addToBackStack(null)
//                .commitAllowingStateLoss()


            val bottomSheet = DescriptionSheetDialog()
            fragmentManager?.let { it1 -> bottomSheet.show(it1, "exampleBottomSheet") }

        }


        val divider = SpacingItemDecorator(20)

        with(recyclerView){
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            addItemDecoration(divider)
        }

        fab.setOnClickListener{
            val searchFragment = SearchFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainLayout,
                    searchFragment,
                    "searchFragment started"
                )
                .addToBackStack(null)
                .commitAllowingStateLoss()


        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    private fun setupObserver() {
        viewModel.getMovies()?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    val movies = it.data?.map { it -> it.toMovieItem() }
                    renderList(movies!!)
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(movies: List<MovieItem>) {
        movieAdapter.updateData(movies)
    }

    private fun initViewModel(){
        viewModel =  ViewModelProviders.of(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))).get(MainViewModel::class.java)
    }
}