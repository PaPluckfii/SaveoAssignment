package com.sumeet.saveoassignment.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import kotlinx.android.synthetic.main.show_item_layout.view.*

class ShowListViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    fun setData(item : ShowListResponseItem){
        Glide.with(itemView).load(item.image?.medium).into(itemView.ivShowImage)
    }

}