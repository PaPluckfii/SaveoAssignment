package com.sumeet.saveoassignment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponse
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import com.sumeet.saveoassignment.data.remote.ApiClient
import com.sumeet.saveoassignment.data.remote.Network
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ShowsRepository {

    private var showsList : MutableLiveData<ShowListResponse> = MutableLiveData()

    fun getLiveData() : LiveData<ShowListResponse>{
        return showsList
    }

    fun getShowsList(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val client = Network.getRetrofitInstance().create(ApiClient::class.java)
            val call = client.getShowsList(page)
            call.enqueue(object : Callback<ShowListResponse>{
                override fun onResponse(
                    call: Call<ShowListResponse>,
                    response: Response<ShowListResponse>
                ) {
                    Log.d("response","Response Obtained")
                    if(response.body() != null){
                        showsList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ShowListResponse>, t: Throwable) {
                    Log.d("response","Failed to get Response")
                }

            })
        }
    }

}