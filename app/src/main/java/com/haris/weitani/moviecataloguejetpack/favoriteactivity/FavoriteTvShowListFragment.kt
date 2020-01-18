package com.haris.weitani.moviecataloguejetpack.favoriteactivity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.ViewModelFactory
import com.haris.weitani.moviecataloguejetpack.adapter.FavoriteTvShowAdapter
import com.haris.weitani.moviecataloguejetpack.adapter.TvShowAdapter
import com.haris.weitani.moviecataloguejetpack.mainactivity.MovieListFragment
import kotlinx.android.synthetic.main.fragment_tv_show_list.*
import kotlinx.android.synthetic.main.fragment_tv_show_list.progressBar

class FavoriteTvShowListFragment : Fragment() {

    private lateinit var adapter: FavoriteTvShowAdapter
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
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            favViewModel = obtainViewModel(requireActivity())
            showLoading(true)
            initTvShowRVList()
        }
    }

    private fun initTvShowRVList() {

        adapter = FavoriteTvShowAdapter()
        adapter.notifyDataSetChanged()

        favViewModel.getFavTvShows().observe(this, Observer {
            adapter.submitList(it)
            showLoading(false)
        })

        rv_tvshow_list.layoutManager = LinearLayoutManager(context)
        rv_tvshow_list.adapter = adapter
        rv_tvshow_list.setHasFixedSize(true)

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

}
