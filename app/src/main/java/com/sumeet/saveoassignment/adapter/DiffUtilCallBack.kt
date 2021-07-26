package com.sumeet.saveoassignment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem

class DiffUtilCallBack : DiffUtil.ItemCallback<ShowListResponseItem>() {
    override fun areItemsTheSame(
        oldItem: ShowListResponseItem,
        newItem: ShowListResponseItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShowListResponseItem,
        newItem: ShowListResponseItem
    ): Boolean {
        return oldItem.id == newItem.id
    }
}