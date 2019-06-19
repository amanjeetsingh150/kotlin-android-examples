package com.developers.usingroom

import androidx.room.Room
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.developers.usingroom.adapters.MyAdapter
import org.jetbrains.anko.doAsync
import java.util.logging.Logger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var database: MyDataBase
        val log = Logger.getLogger(MainActivity::class.java.name)
    }

    lateinit var superheroList: List<SuperHero>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Room.databaseBuilder(this,
                MyDataBase::class.java, "room-db")
                .build()
        val superhero1 = SuperHero("SpiderMan", "Marvel", "WEB")
        val superhero2 = SuperHero("SuperMan", "DC", "KRYPTONITE")
        val superhero3 = SuperHero("BatMan", "DC", "MONEY")
        doAsync {
            database.superheroDao().insert(superhero1)
            database.superheroDao().insert(superhero2)
            database.superheroDao().insert(superhero3)
            superheroList = database.superheroDao().getAllHeroes()
            runOnUiThread {
                log.info("DATA" + superheroList)
                superhero_list_recycler_view.layoutManager = LinearLayoutManager(applicationContext,
                        LinearLayout.VERTICAL, false)
                superhero_list_recycler_view.adapter = MyAdapter(applicationContext,
                        superheroList)
            }
        }
    }
}
