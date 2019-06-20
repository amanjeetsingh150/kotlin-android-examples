package com.developers.usingroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Amanjeet Singh on 14/11/17.
 */
@Entity(tableName = "superhero")
data class SuperHero(
                     @ColumnInfo(name = "superhero_name")
                     var SuperheroName: String,
                     @ColumnInfo(name = "superhero_series")
                     var SuperHeroSeries: String,
                     @ColumnInfo(name = "power")
                     var Power: String){
    @ColumnInfo(name = "superhero_id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}