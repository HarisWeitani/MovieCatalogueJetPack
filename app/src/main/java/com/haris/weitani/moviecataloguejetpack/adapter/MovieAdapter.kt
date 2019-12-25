package com.haris.weitani.moviecataloguejetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.data.Movie
import com.haris.weitani.moviecataloguejetpack.detail_view.MovieDetailView
import kotlinx.android.synthetic.main.rv_layout_item_movie.view.*
import org.jetbrains.anko.intentFor

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<Movie>()

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
        fun bind(data: Movie) {
            with(itemView) {

                iv_movie_poster.setImageResource(data.picture)
                tv_movie_title.text = data.name
                tv_movie_description.setText(data.desc)

                itemView.setOnClickListener {
                    context.startActivity(
                        context.intentFor<MovieDetailView>(GlobalVal.SELECTED_MOVIE to data.id)
                    )
                }
            }
        }
    }

    fun setMovieData(items: ArrayList<Movie>) {
        listMovie.clear()
        listMovie.addAll(items)
        notifyDataSetChanged()
    }
}