package com.developers.contentproviders.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

/**
 * Created by Amanjeet Singh on 25/11/17.
 */
@Database(entities = arrayOf(Villains::class), version = 1)
abstract class VillainsDatabase : RoomDatabase() {

    abstract fun villainDao(): VillainsDao

    companion object {

        private lateinit var villainDatabase: VillainsDatabase

        fun villainDatabaseInstance(context: Context): VillainsDatabase {
            villainDatabase = Room.databaseBuilder(context,
                    VillainsDatabase::class.java, "room-db").build()
            villainDatabase.insertDummyData()
            return villainDatabase
        }
    }

    private fun insertDummyData() {
        if (villainDao().count() == 0) {
            beginTransaction()
            try {
                for (i in 0..4) {
                    val villain = Villains(Villains.villainsName[i], Villains.villainsSeries[i])
                    villainDao().insert(villain)
                }
                setTransactionSuccessful()
            } catch (exception: Exception) {
                exception.printStackTrace()
            } finally {
                endTransaction()
            }

        }
    }
}