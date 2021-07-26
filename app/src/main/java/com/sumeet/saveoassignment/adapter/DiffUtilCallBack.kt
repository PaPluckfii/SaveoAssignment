package com.sumeet.saveoassignment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem

/** To check if newly obtained data is equal to the old data */


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