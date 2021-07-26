package com.sumeet.saveoassignment.view.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import com.sumeet.saveoassignment.repository.ShowsRepository

class ShowItemViewModel : ViewModel() {

    private val repository = ShowsRepository

    fun callApi(id : Int) {
        return repository.getMovieDetails(id)
    }

    fun getMovie() : LiveData<ShowListResponseItem>{
        return repository.getLiveMovie()
    }

}