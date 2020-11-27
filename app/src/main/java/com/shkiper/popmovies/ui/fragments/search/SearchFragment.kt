package com.shkiper.popmovies.ui.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.shkiper.popmovies.R
import com.shkiper.popmovies.models.Movie
import com.shkiper.popmovies.retrofit.ApiHelperImpl
import com.shkiper.popmovies.retrofit.RetrofitBuilder
import com.shkiper.popmovies.ui.adapters.MoviesAdapter
import com.shkiper.popmovies.ui.fragments.description.DescriptionSheetDialog
import com.shkiper.popmovies.util.AppConstants
import com.shkiper.popmovies.util.Status
import com.shkiper.popmovies.util.ViewModelFactory
import com.shkiper.popmovies.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViewModel()


        search_button.setOnClickListener {
            val query = search_edit_text.text.toString().trim()
            if (query.isBlank()) {
                Snackbar.make(view, getString(R.string.search_alert), Snackbar.LENGTH_LONG).show()
            } else {
                findMovies(query)
            }
        }
    }

    private fun findMovies(query: String) {
        showLoader()
        viewModel.findMovies(query)
        viewModel.getMovies()?.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    hideLoader()
                    showMovies(it.data!!)
                }
                Status.LOADING ->{
                    showLoader()
                }
                Status.ERROR ->{
                    hideLoader()
                    showError()
                }
            }
        })
    }

    private fun showLoader() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        progress_bar.visibility = View.GONE
    }

    private fun showEmptyMovies() {
        results_recycler_view.visibility = View.GONE
        no_results_placeholder.visibility = View.VISIBLE
    }

    private fun showError() {
        results_recycler_view.visibility = View.GONE
        no_results_placeholder.visibility = View.VISIBLE
        no_results_placeholder.text = no_results_placeholder.context.getString(R.string.error)
    }

    private fun showMovies(movies: List<Movie>) {
        val moviesAdapter = MoviesAdapter{
            val bundle = Bundle()
            bundle.putString(AppConstants.MOVIE_ID, it.id)
            val descriptionDialog = DescriptionSheetDialog.getNewInstance(bundle)
            descriptionDialog.show(childFragmentManager, "startDescriptionDialog")
        }
        moviesAdapter.updateData(movies.map { it.toMovieItem() })
        results_recycler_view.adapter = moviesAdapter
        results_recycler_view.visibility = View.VISIBLE
        no_results_placeholder.visibility = View.GONE
    }

    private fun initViewModel(){
        viewModel =  ViewModelProviders.of(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService))).get(
            SearchViewModel::class.java)
    }

}