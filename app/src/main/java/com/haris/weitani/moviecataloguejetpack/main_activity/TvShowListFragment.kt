package com.haris.weitani.moviecataloguejetpack.common

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.haris.weitani.moviecataloguejetpack.main_activity.MainViewModel
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.adapter.TvShowAdapter

class TvShowListFragment : Fragment() {

    private lateinit var adapter : TvShowAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_tv_show_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}
