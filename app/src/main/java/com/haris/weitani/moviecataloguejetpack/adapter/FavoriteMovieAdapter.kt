package com.haris.weitani.moviecataloguejetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultGetMovie
import com.haris.weitani.moviecataloguejetpack.detailview.MovieDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_layout_item_movie.view.*
import org.jetbrains.anko.intentFor

class FavoriteMovieAdapter() : PagedListAdapter<ResultGetMovie,FavoriteMovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private var listMovie = ArrayList<ResultGetMovie>()

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<ResultGetMovie>() {
            override fun areItemsTheSame(oldItem: ResultGetMovie, newItem: ResultGetMovie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultGetMovie, newItem: ResultGetMovie) =
                oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_layout_item_movie,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: ResultGetMovie) {
            with(itemView) {

                Picasso.get()
                    .load(GlobalVal.POSTER_BASE_URL + data.poster_path)
                    .into(iv_movie_poster)

                tv_movie_title.text = data.title
                tv_movie_description.text = data.overview

                itemView.setOnClickListener {
                    context.startActivity(
                        context.intentFor<MovieDetailView>(GlobalVal.SELECTED_MOVIE to data.id)
                    )
                }
            }
        }
    }

    fun setMovieData(items: ArrayList<ResultGetMovie>) {
        listMovie.clear()
        listMovie.addAll(items)
        notifyDataSetChanged()
    }
}