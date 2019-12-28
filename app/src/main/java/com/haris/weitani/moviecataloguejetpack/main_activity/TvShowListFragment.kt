package com.haris.weitani.moviecataloguejetpack.common

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.haris.weitani.moviecataloguejetpack.main_activity.MainViewModel
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.adapter.TvShowAdapter
import com.haris.weitani.moviecataloguejetpack.data.TvShow
import kotlinx.android.synthetic.main.fragment_tv_show_list.*

class TvShowListFragment : Fragment() {

    private lateinit var adapter : TvShowAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TvShowAdapter()
        adapter.notifyDataSetChanged()

        rv_tvshow_list.layoutManager = LinearLayoutManager(requireContext())
        rv_tvshow_list.adapter = adapter

        mainViewModel.setTvShow()
        mainViewModel.getTvShow().observe(this, Observer {
            adapter.setTvShowData(it)
        })
    }


}
