package com.sumeet.saveoassignment.adapter

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponse
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import com.sumeet.saveoassignment.data.remote.ApiClient
import com.sumeet.saveoassignment.view.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/** Here we call api inorder to get new data, we increase the page number while calling the api */

class ShowListPagingSource(private val apiClient: ApiClient) :
    PagingSource<Int, ShowListResponseItem>() {

    companion object {
        private const val FIRST_PAGE_INDEX: Int = 1
    }

    override fun getRefreshKey(state: PagingState<Int, ShowListResponseItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ShowListResponseItem> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            //val client = Network.getRetrofitInstance().create(ApiClient::class.java)
            var list = mutableListOf<ShowListResponseItem>()
            val call = apiClient.getShowsList(nextPage)
            call.enqueue(object : Callback<ShowListResponse> {
                override fun onResponse(
                    call: Call<ShowListResponse>,
                    response: Response<ShowListResponse>
                ) {
                    Log.d("response", "Response Obtained")
                    if (response.body() != null) {
                        val data = response.body() ?: emptyList<ShowListResponseItem>()
                        list.addAll(data)
                    }
                }

                override fun onFailure(call: Call<ShowListResponse>, t: Throwable) {
                    Log.d("response", "Failed to get Response")
                }

            })

            val nextPageNum = nextPage + 1

            Log.d("checking", "here $nextPage")

            LoadResult.Page(
                data = list,
                prevKey = if (nextPage == FIRST_PAGE_INDEX) null
                else nextPage,
                nextKey = nextPageNum
            )

        } catch (e: Exception) {
            Log.d("checking", "exception $e")
            LoadResult.Error(e)
        }
    }
}