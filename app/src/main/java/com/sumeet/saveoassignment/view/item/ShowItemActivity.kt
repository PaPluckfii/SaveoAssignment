package com.sumeet.saveoassignment.view.item

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sumeet.saveoassignment.R
import com.sumeet.saveoassignment.data.model.showlist.ShowListResponseItem
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_home_screen.toolbarHome
import kotlinx.android.synthetic.main.activity_show_item.*

class ShowItemActivity : AppCompatActivity() {

    lateinit var viewModel: ShowItemViewModel
    lateinit var showListResponseItem: ShowListResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_item)

        /** initializing views */
        initAll()

        /** getting id and calling api */
        val id = intent.getIntExtra("id",1)
        callApi(id)

        /** observing data */
        observeData()

    }

    private fun observeData() {
        viewModel.getMovie().observe(this, Observer {
            showListResponseItem = it
            setView()
        })
    }

    private fun initAll() {
        setSupportActionBar(toolbarHome)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)

        viewModel = ViewModelProvider(this).get(ShowItemViewModel::class.java)
    }

    private fun setView() {
        detailedProgressBar.visibility = View.GONE
        content.visibility = View.VISIBLE
        showDetailTitle.text = showListResponseItem.name
        tvMovieName.text = showListResponseItem.name
        val str = StringBuffer()
        for(i in showListResponseItem.genres!!)
            str.append("$i ")
        tv_genre.text = str.toString()
        Glide.with(this).load(showListResponseItem.image?.original).into(ivDetailedViewImage)
    }

    private fun callApi(id: Int) {
        viewModel.callApi(id)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{ NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}