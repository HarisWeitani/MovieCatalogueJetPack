package com.haris.weitani.moviecataloguejetpack.mainactivity

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.ViewModelFactory
import com.haris.weitani.moviecataloguejetpack.adapter.TvShowAdapter
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.vo.Status
import kotlinx.android.synthetic.main.fragment_tv_show_list.*

class TvShowListFragment : Fragment() {

    private lateinit var adapter: TvShowAdapter
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
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isLoading(true)
        if (activity != null) {

            mainViewModel = obtainViewModel(activity!!)

            adapter = TvShowAdapter()
            adapter.notifyDataSetChanged()

            rv_tvshow_list.layoutManager = LinearLayoutManager(requireContext())
            rv_tvshow_list.adapter = adapter

            mainViewModel.tvShows?.observe(this, Observer {
                if (it != null) {
                    when (it.status) {
                        Status.SUCCESS -> {
                            isLoading(false)
                            adapter.setTvShowData(it.data as ArrayList<ResultTvShow>)
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
