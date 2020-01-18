package com.haris.weitani.moviecataloguejetpack.favoriteactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.ViewModelFactory
import com.haris.weitani.moviecataloguejetpack.adapter.FavoriteMovieAdapter
import com.haris.weitani.moviecataloguejetpack.adapter.MovieAdapter
import com.haris.weitani.moviecataloguejetpack.mainactivity.MovieListFragment
import kotlinx.android.synthetic.main.fragment_movie_list.*

class FavoriteMovieListFragment : Fragment() {

    private lateinit var adapter: FavoriteMovieAdapter
    private lateinit var favViewModel: FavoriteViewModel

    companion object {

        fun newInstance(): Fragment = MovieListFragment()

        private fun obtainViewModel(activity: FragmentActivity): FavoriteViewModel {
            val factory: ViewModelFactory? = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(FavoriteViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            favViewModel = obtainViewModel(requireActivity())
            showLoading(true)
            initMovieRVList()
        }
    }

    private fun initMovieRVList() {

        adapter = FavoriteMovieAdapter()
        adapter.notifyDataSetChanged()

        favViewModel.getFavMovies().observe(this, Observer {
            adapter.submitList(it)
            showLoading(false)
        })

        rv_movie_list.layoutManager = LinearLayoutManager(context)
        rv_movie_list.adapter = adapter
        rv_movie_list.setHasFixedSize(true)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

}
