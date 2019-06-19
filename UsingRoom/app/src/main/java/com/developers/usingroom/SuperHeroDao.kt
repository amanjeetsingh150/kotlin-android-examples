package com.developers.usingroom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

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