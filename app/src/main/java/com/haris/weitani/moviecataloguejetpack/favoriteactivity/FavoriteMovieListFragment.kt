package com.haris.weitani.moviecataloguejetpack.favoriteactivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*

class FavoriteMovieListFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var favViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMovieRVList()
        initMainViewModel()
    }

    override fun onResume() {
        super.onResume()
        initMainViewModel()
    }

    private fun initMovieRVList() {

        adapter = MovieAdapter()
        adapter.notifyDataSetChanged()

        rv_movie_list.layoutManager = LinearLayoutManager(context)
        rv_movie_list.adapter = adapter

    }

    private fun initMainViewModel() {
        favViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FavoriteViewModel::class.java)
        favViewModel.setMovie(requireContext())
        showLoading(true)

        favViewModel.getMovie().observe(this, Observer {
            if (it != null) {
                adapter.setMovieData(it)
                showLoading(false)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

}
