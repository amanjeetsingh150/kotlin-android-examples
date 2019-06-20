package com.developers.contentproviders


import android.content.ContentValues
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.developers.contentproviders.adapter.VillainAdapter
import com.developers.contentproviders.data.Villains
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private lateinit var mAdapter: VillainAdapter

    companion object {
        const val LOADER_VILLAIN = 1
        val log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(applicationContext)
        doAsync {
            //Insert through content provider
            val values = ContentValues()
            values.put(Villains.VILLAIN_NAME, "Gustavo Fring")
            values.put(Villains.VILLAIN_SERIES, "Breaking Bad")
            contentResolver.insert(VillainProvider.uri, values)
        }
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler_view.layoutManager = layoutManager
        supportLoaderManager.initLoader(LOADER_VILLAIN, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        when (id) {
            LOADER_VILLAIN -> {
                return CursorLoader(applicationContext, VillainProvider.uri, arrayOf(Villains.VILLAIN_NAME,
                        Villains.VILLAIN_SERIES, Villains.COLUMN_ID), null, null, null)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        when (loader.id) {
            LOADER_VILLAIN -> {
                log.info("In RESET")
            }
        }
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        when (loader.id) {
            LOADER_VILLAIN -> {
                mAdapter = VillainAdapter(applicationContext)
                recycler_view.adapter = mAdapter
                mAdapter.setVillains(data as Cursor)
            }
        }
    }
}
