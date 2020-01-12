package com.haris.weitani.moviecataloguejetpack.mainactivity

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.ViewModelFactory
import com.haris.weitani.moviecataloguejetpack.adapter.MovieAdapter
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.vo.Status
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var mainViewModel: MainViewModel

    companion object {

        fun newInstance(): Fragment = MovieListFragment()

        private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
            val factory: ViewModelFactory? = ViewModelFactory.getInstance(activity.application)
            return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
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
        isLoading(true)
        if (activity != null) {
            mainViewModel = obtainViewModel(activity!!)
            adapter = MovieAdapter()
            adapter.notifyDataSetChanged()

            rv_movie_list.layoutManager = LinearLayoutManager(requireContext())
            rv_movie_list.adapter = adapter

            mainViewModel.movies?.observe(this, Observer {
                if (it != null) {
                    when (it.status) {
                        Status.SUCCESS -> {
                            isLoading(false)
                            adapter.setMovieData(it.data as ArrayList<ResultGetMovie>)
                        }
                        Status.ERROR -> {
                            isLoading(false)
                        }
                        Status.LOADING -> {
                            isLoading(true)
                        }
                    }
                }
            })
        }
    }

    private fun isLoading(status : Boolean){
        if(status){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }
    }
}
