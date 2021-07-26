package com.sumeet.saveoassignment.adapter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponse
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import com.sumeet.saveoassignment.data.remote.ApiClient
import com.sumeet.saveoassignment.view.home.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ShowListPagingSource(private val apiClient: ApiClient) : PagingSource<Int, ShowListResponseItem>() {

    companion object{
        private var FIRST_PAGE_INDEX : Int = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ShowListResponseItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowListResponseItem> {
        return try {
            val nextPage : Int = params.key ?: FIRST_PAGE_INDEX
            val nextPageNum = nextPage + 1
            var result : List<ShowListResponseItem>? = null
            apiClient.getShowsList(nextPage).enqueue(
                object : Callback<ShowListResponse> {
                    override fun onResponse(
                        call: Call<ShowListResponse>,
                        response: Response<ShowListResponse>
                    ) {
                        if(response.body() != null){
                            result = response.body()
                        }
                    }
                    override fun onFailure(call: Call<ShowListResponse>, t: Throwable) {
                        Log.d("checking","failed $nextPage")
                    }
                }
            )

            Log.d("checking","here $nextPage")

            LoadResult.Page(
                data = result!!,
                prevKey = null,
                nextKey = nextPageNum
            )

        }catch (e : Exception){
            Log.d("checking","exception $e")
            LoadResult.Error(e)
        }
    }
}