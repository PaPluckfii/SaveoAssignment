package com.sumeet.saveoassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumeet.saveoassignment.R
import com.sumeet.saveoassignment.data.model.showlist.Image
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import com.sumeet.saveoassignment.view.tempadapter.ItemListClickListener
import kotlinx.android.synthetic.main.horizontal_item_layout.view.*

/** For horizontal scroll */

class HorizontalAdapter(private val data : List<ShowListResponseItem>,private val itemListClickListener: ItemListClickListener) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>(){
    class ViewHolder(view:View, private val itemListClickListener: ItemListClickListener) : RecyclerView.ViewHolder(view) {
        fun setImage(data: ShowListResponseItem) {
            Glide.with(itemView).load(data.image?.original).into(itemView.ivHorizontalItem)

            itemView.ivHorizontalItem.setOnClickListener{
                data.id?.let { it1 -> itemListClickListener.onItemButtonClicked(it1) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_item_layout,parent,false)
        return HorizontalAdapter.ViewHolder(inflate,itemListClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setImage(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}