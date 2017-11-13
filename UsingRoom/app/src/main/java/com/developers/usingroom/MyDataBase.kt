package com.developers.usingroom

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by Amanjeet Singh on 14/11/17.
 */
@Database(entities = arrayOf(SuperHero::class), version = 1, exportSchema = false)
abstract class MyDataBase:RoomDatabase(){
    abstract fun superheroDao():SuperHeroDao
}