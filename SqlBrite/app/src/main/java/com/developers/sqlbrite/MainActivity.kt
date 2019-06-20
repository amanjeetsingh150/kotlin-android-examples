package com.developers.sqlbrite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developers.sqlbrite.db.RepoDB
import com.squareup.sqlbrite3.BriteDatabase
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var briteDatabase: BriteDatabase
    private var repoModel: RepoModel? = null
    private val repoList = mutableListOf<RepoModel?>()
    private var repoDisposable: Disposable? = null

    companion object {
        val log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InitApp.getComponent(this).inject(this)

        val repos = briteDatabase.createQuery(RepoDB.TABLE_NAME,
                "SELECT * FROM " + RepoDB.TABLE_NAME)
        repoDisposable = repos.mapToOne { cursor ->
            try {
                cursor.moveToLast()
                val name = cursor.getString(cursor.getColumnIndex(RepoDB.name))
                val repo = cursor.getString(cursor.getColumnIndex(RepoDB.repo))
                repoModel = RepoModel(repo, name)
                repoList.add(repoModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return@mapToOne repoList
        }.subscribe({ m ->
            runOnUiThread {
                owner_text.append(m[m.size - 1]?.ownerName + ", ")
                repo_text.append(m[m.size - 1]?.repoName + ", ")
            }
        },
                { e -> e.printStackTrace() })
        insert_button.setOnClickListener {
            briteDatabase.insert(RepoDB.TABLE_NAME, SQLiteDatabase.CONFLICT_ABORT,
                    createRepo(repo_name_edittext.text.toString(), name_edittext.text.toString()))
        }

    }

    private fun createRepo(repoName: String, name: String): ContentValues {
        val contentValues = ContentValues()
        with(contentValues) {
            put(RepoDB.name, name)
            put(RepoDB.repo, repoName)
        }
        return contentValues
    }

    override fun onStop() {
        super.onStop()
        repoDisposable?.dispose()
    }
}
