package com.sumeet.saveoassignment.data.remote

import com.sumeet.saveoassignment.data.model.showlist.ShowListResponse
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("/shows")
    fun getShowsList(@Query("page") page : Int) : Call<ShowListResponse>

}