package com.developers.listadapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developers.listadapter.adapter.LanguageAdapter
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.DividerItemDecoration


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var languagesAdapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.getLanguageList().observe(this, Observer {
            languagesAdapter.submitList(it)
        })
        languagesAdapter = LanguageAdapter()
        val linearLayoutManager = LinearLayoutManager(this).apply {
            orientation = RecyclerView.VERTICAL
        }
        languagesRecyclerView.layoutManager = linearLayoutManager
        val dividerItemDecoration =
            DividerItemDecoration(languagesRecyclerView.context, linearLayoutManager.orientation)
        languagesRecyclerView.addItemDecoration(dividerItemDecoration)
        languagesRecyclerView.adapter = languagesAdapter
        swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.refreshList()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}
