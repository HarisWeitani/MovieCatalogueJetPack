package com.haris.weitani.moviecataloguejetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haris.weitani.moviecataloguejetpack.R
import com.haris.weitani.moviecataloguejetpack.common.GlobalVal
import com.haris.weitani.moviecataloguejetpack.data.TvShow
import com.haris.weitani.moviecataloguejetpack.data.remote.ResultTvShow
import com.haris.weitani.moviecataloguejetpack.detailview.TvShowDetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_layout_item_tv_show.view.*
import org.jetbrains.anko.intentFor

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShow = ArrayList<ResultTvShow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder =
        TvShowViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_layout_item_tv_show,parent,false))

    override fun getItemCount(): Int = listTvShow.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    inner class TvShowViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind( data : ResultTvShow){
            with(itemView){

                Picasso.get()
                    .load(GlobalVal.POSTER_BASE_URL+data.poster_path)
                    .into(iv_tvshow_poster)

                tv_tvshow_title.text = data.name
                tv_tvshow_description.text = data.overview

                itemView.setOnClickListener {
                    context.startActivity(
                        context.intentFor<TvShowDetailView>(GlobalVal.SELECTED_TV_SHOW to data.id)
                    )
                }
            }
        }
    }

    fun setTvShowData(items : ArrayList<ResultTvShow>){
        listTvShow.clear()
        listTvShow.addAll(items)
        notifyDataSetChanged()
    }

}