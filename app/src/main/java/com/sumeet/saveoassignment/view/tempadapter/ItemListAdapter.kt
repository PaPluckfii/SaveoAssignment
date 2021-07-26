package com.sumeet.saveoassignment.view.tempadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sumeet.saveoassignment.R
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem


class ItemListAdapter(
    private var listOfItems : List<ShowListResponseItem>,
    private val itemListClickListener: ItemListClickListener
) : RecyclerView.Adapter<ItemListViewHolder>() {

    //private var responseList = arrayListOf<ResponseModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.show_item_layout,parent,false)
        return ItemListViewHolder(view,itemListClickListener)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        holder.setData(listOfItems[position])
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

}