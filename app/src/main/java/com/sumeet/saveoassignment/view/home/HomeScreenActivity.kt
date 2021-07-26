package com.sumeet.saveoassignment.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumeet.saveoassignment.R
import com.sumeet.saveoassignment.adapter.ShowListAdapter
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import com.sumeet.saveoassignment.view.tempadapter.ItemListAdapter
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.coroutines.flow.collectLatest

class HomeScreenActivity : AppCompatActivity() {

    private lateinit var recyclerAdapter: ShowListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setSupportActionBar(toolbarHome)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        recyclerAdapter = ShowListAdapter()
        var list : List<ShowListResponseItem> = mutableListOf()



        viewModel.callApiForShowList(1)


        recyclerViewHome.apply {
            layoutManager = LinearLayoutManager(this@HomeScreenActivity)

        }

        viewModel.getLiveData().observe(this, Observer {
            val newAdapt = ItemListAdapter(it)
            recyclerViewHome.adapter = newAdapt
            newAdapt.notifyDataSetChanged()
        })

//        lifecycleScope.launchWhenCreated {
//            viewModel.getShowList().collectLatest {
//                recyclerAdapter.submitData(it)
//            }
//        }

    }
}