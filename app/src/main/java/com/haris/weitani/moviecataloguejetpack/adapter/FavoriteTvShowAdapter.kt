package com.haris.weitani.moviecataloguejetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.detailview.TvShowDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_layout_item_tv_show.view.*
import org.jetbrains.anko.intentFor

class FavoriteTvShowAdapter : PagedListAdapter<ResultTvShow, FavoriteTvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK)
{

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ResultTvShow>() {
            override fun areItemsTheSame(oldItem: ResultTvShow, newItem: ResultTvShow) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultTvShow, newItem: ResultTvShow) =
                oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder =
        TvShowViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_layout_item_tv_show,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: ResultTvShow?) {
            with(itemView) {

                Picasso.get()
                    .load(GlobalVal.POSTER_BASE_URL + data?.poster_path)
                    .into(iv_tvshow_poster)

                tv_tvshow_title.text = data?.name
                tv_tvshow_description.text = data?.overview

                itemView.setOnClickListener {
                    context.startActivity(
                        context.intentFor<TvShowDetailView>(GlobalVal.SELECTED_TV_SHOW to data?.id)
                    )
                }
            }
        }
    }
}