package com.developers.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val androidName = listOf<String>("Cupcake", "Donut", "Eclair", "Froyo",
            "Gingerbread", "Honeycomb", "Ice Cream Sandwich", "JellyBean", "Kitkat",
            "Lollipop", "Marshmallow", "Nougat", "Oreo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view_name.layoutManager=LinearLayoutManager(this,
                LinearLayout.VERTICAL,false)
        recycler_view_name.adapter=MyAdapter(androidName,this)
    }
}
