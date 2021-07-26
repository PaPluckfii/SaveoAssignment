package com.sumeet.saveoassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.sumeet.saveoassignment.R
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem

class ShowListAdapter : PagingDataAdapter<ShowListResponseItem, ShowListViewHolder>(DiffUtilCallBack()) {
    override fun onBindViewHolder(holder: ShowListViewHolder, position: Int) {
        getItem(position)?.let { holder.setData(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item_layout,parent,false)
        return ShowListViewHolder(view)
    }
}