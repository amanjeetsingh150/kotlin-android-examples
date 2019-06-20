package com.developers.sqlbrite.db

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase


/**
 * Created by Amanjeet Singh on 8/2/18.
 */
class RepoDB : SupportSQLiteOpenHelper.Callback(1) {


    companion object {
        @JvmField
        val TABLE_NAME = "REPO"
        @JvmField
        val ID = "ID"
        @JvmField
        val repo = "REPO"
        @JvmField
        val name = "OWNER"
    }

    private val CREATE_REPO_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            name + " TEXT NOT NULL," +
            repo + " TEXT NOT NULL)"

    override fun onCreate(db: SupportSQLiteDatabase?) {
        db?.execSQL(CREATE_REPO_TABLE)
        val contentValues = ContentValues()
        contentValues.put(name, "amanjeetsingh150")
        contentValues.put(repo, "UberCarAnimation")
        db?.insert(TABLE_NAME, SQLiteDatabase.CONFLICT_ABORT, contentValues)
    }

    override fun onUpgrade(db: SupportSQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}