package com.sumeet.saveoassignment.view.tempadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import kotlinx.android.synthetic.main.show_item_layout.view.*

class ItemListViewHolder(
    private val itemView : View,
    private val itemListClickListener: ItemListClickListener
) : RecyclerView.ViewHolder(itemView) {

    fun setData(responseModelItem: ShowListResponseItem){

        Glide.with(itemView).load(responseModelItem.image?.medium).into(itemView.ivShowImage)

        itemView.ivShowImage.setOnClickListener {
            responseModelItem.id?.let { it1 -> itemListClickListener.onItemButtonClicked(it1) }
        }
    }

}
