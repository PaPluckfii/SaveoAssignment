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
    private var movie : MutableLiveData<ShowListResponseItem> = MutableLiveData()

    /** Exposing live data of list of shows */

    fun getLiveData() : LiveData<ShowListResponse>{
        return showsList
    }

    /** Exposing live data of currently selected item */

    fun getLiveMovie() : LiveData<ShowListResponseItem>{
        return movie
    }

    /** Api Call for getting list of items */

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

    /** Api call to get item details */

    fun getMovieDetails(id: Int) {
        movie.postValue(showsList.value?.get(0))
        CoroutineScope(Dispatchers.IO).launch {
            val client = Network.getRetrofitInstance().create(ApiClient::class.java)
            val call = client.getMovieDetails(id)
            call.enqueue(object : Callback<ShowListResponseItem>{
                override fun onResponse(
                    call: Call<ShowListResponseItem>,
                    response: Response<ShowListResponseItem>
                ) {
                    Log.d("response","Response Obtained")
                    movie.postValue(response.body())
                }

                override fun onFailure(call: Call<ShowListResponseItem>, t: Throwable) {
                    Log.d("detailed response","Failed to get Response")
                }

            })
        }
    }

}