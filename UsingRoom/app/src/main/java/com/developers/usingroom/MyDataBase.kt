package com.developers.usingroom

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Amanjeet Singh on 14/11/17.
 */
@Database(entities = arrayOf(SuperHero::class), version = 1, exportSchema = false)
abstract class MyDataBase:RoomDatabase(){
    abstract fun superheroDao():SuperHeroDao
}