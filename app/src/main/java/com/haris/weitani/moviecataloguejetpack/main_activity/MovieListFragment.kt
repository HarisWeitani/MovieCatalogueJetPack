package com.haris.weitani.moviecataloguejetpack.common

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.haris.weitani.moviecataloguejetpack.main_activity.MainViewModel
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.adapter.MovieAdapter

class MovieListFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
