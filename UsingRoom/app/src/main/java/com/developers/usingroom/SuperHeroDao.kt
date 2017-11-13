package com.developers.usingroom

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

/**
 * Created by Amanjeet Singh on 14/11/17.
 */
@Dao
interface SuperHeroDao {

    @Query("Select * from superhero")
    fun getAllHeroes(): List<SuperHero>

    @Insert
    fun insert(superHero: SuperHero)

    @Delete
    fun delete(superHero: SuperHero)
}