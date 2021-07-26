package com.sumeet.saveoassignment.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sumeet.saveoassignment.adapter.ShowListPagingSource
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponse
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import com.sumeet.saveoassignment.data.remote.ApiClient
import com.sumeet.saveoassignment.data.remote.Network
import com.sumeet.saveoassignment.repository.ShowsRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel : ViewModel() {

    private val repository = ShowsRepository

    fun callApiForShowList(page : Int) {
        repository.getShowsList(page)
    }

    fun getLiveData(): LiveData<ShowListResponse> {
        return repository.getLiveData()
    }

    fun getShowList() : Flow<PagingData<ShowListResponseItem>>{
        return Pager(
            config = PagingConfig(pageSize = 1, maxSize = 10),
            pagingSourceFactory = {ShowListPagingSource(Network.getRetrofitInstance().create(ApiClient::class.java))}
        ).flow.cachedIn(viewModelScope)
    }

}