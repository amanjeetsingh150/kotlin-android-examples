package com.developers.diffutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger
import androidx.recyclerview.widget.DividerItemDecoration



class MainActivity : AppCompatActivity() {

    private lateinit var contactAdapter: ContactsAdapter

    companion object {
        val log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_view_main.layoutManager = linearLayoutManager
        val dataSource = DataSource()
        contactAdapter = ContactsAdapter(applicationContext, dataSource.getData())
        recycler_view_main.adapter = contactAdapter
        val dividerItemDecoration = DividerItemDecoration(recycler_view_main.context,
                linearLayoutManager.orientation)
        recycler_view_main.addItemDecoration(dividerItemDecoration)
        swipeRefreshLayout.setOnRefreshListener {
            contactAdapter.updateData(dataSource.getUpdatedData())
            log.info(dataSource.getUpdatedData()[0].status)
            swipeRefreshLayout.isRefreshing = false
        }
    }
}
