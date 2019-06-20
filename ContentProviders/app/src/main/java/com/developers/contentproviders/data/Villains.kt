package com.developers.contentproviders.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.content.ContentValues
import android.provider.BaseColumns


/**
 * Created by Amanjeet Singh on 25/11/17.
 */
@Entity(tableName = Villains.TABLE_NAME)
data class Villains(
        @ColumnInfo(name = VILLAIN_NAME)
        var villainName: String,
        @ColumnInfo(name = VILLAIN_SERIES)
        var villainSeries: String,
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(index = true, name = COLUMN_ID)
        var id: Long = 0) {


    companion object {

        const val COLUMN_ID = BaseColumns._ID
        const val TABLE_NAME = "villains"
        const val VILLAIN_NAME = "villain_name"
        const val VILLAIN_SERIES = "series"
        val villainsName = listOf("Joker", "DeathStroke", "Reverse Flash", "Lex Luthor", "Harley Quinn")
        val villainsSeries = listOf("Batman", "Arrow", "Flash", "Superman", "Suicide Squad")
        var villain: Villains = Villains("", "")
        fun fromContentValues(vals: ContentValues): Villains {
            if (vals.containsKey(COLUMN_ID)) {
                villain.id = vals.getAsLong(COLUMN_ID)
            }
            if (vals.containsKey(VILLAIN_NAME)) {
                villain.villainName = vals.getAsString(VILLAIN_NAME)
            }
            if (vals.containsKey(VILLAIN_SERIES)) {
                villain.villainSeries = vals.getAsString(VILLAIN_SERIES)
            }
            return villain
        }
    }

}