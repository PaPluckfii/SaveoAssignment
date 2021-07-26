package com.sumeet.saveoassignment.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumeet.saveoassignment.R
import com.sumeet.saveoassignment.adapter.HorizontalAdapter
import com.sumeet.saveoassignment.adapter.ShowListAdapter
import com.sumeet.saveoassignment.adapter.SpacesItemDecoration
import com.sumeet.saveoassignment.data.model.showlist.Image
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import com.sumeet.saveoassignment.view.item.ShowItemActivity
import com.sumeet.saveoassignment.view.tempadapter.ItemListAdapter
import com.sumeet.saveoassignment.view.tempadapter.ItemListClickListener
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity(), ItemListClickListener{

    private lateinit var recyclerAdapter: ShowListAdapter
    private lateinit var viewModel : HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        /** initialize viewmodel, action bar and recycler view */
        initAll()

        /**calling api to get list of items , faced issues with paging and hence just showing result from page 1 */
        viewModel.callApiForShowList(1)

        /**setting up recycler views */
        setRecycler()

        /**to observe live data */
        observeData()

    }

    private fun observeData() {
        viewModel.getLiveData().observe(this, Observer {
            listProgressBar.visibility = View.GONE
            val newAdapt = ItemListAdapter(it,this)
            recyclerViewHome.adapter = newAdapt
            newAdapt.notifyDataSetChanged()

            val list = mutableListOf<ShowListResponseItem>()
            var index = 10
            if(it != null){
                for(i in 0..10){
                    it[index++].let { it1 -> list.add(i, it1) }
                }
            }

            val horizontalAdapter =  HorizontalAdapter(list,this)
            horizontalScrollViewHome.adapter = horizontalAdapter
            horizontalAdapter.notifyDataSetChanged()

        })
    }

    private fun initAll() {
        setSupportActionBar(toolbarHome)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        recyclerAdapter = ShowListAdapter()
    }

    private fun setRecycler() {
        recyclerViewHome.apply {
            layoutManager = GridLayoutManager(this@HomeScreenActivity,3)
            addItemDecoration(SpacesItemDecoration(3,50,false))
        }

        val layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        horizontalScrollViewHome.layoutManager = layoutManager
    }

    override fun onItemButtonClicked(id: Int) {
        val intent = Intent(this@HomeScreenActivity, ShowItemActivity::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }
}