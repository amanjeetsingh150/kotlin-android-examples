package com.developers.contentproviders

import android.content.*
import android.database.Cursor
import android.net.Uri
import com.developers.contentproviders.data.Villains
import com.developers.contentproviders.data.VillainsDatabase

class VillainProvider : ContentProvider() {

    companion object {
        val AUTHORITY: String = "com.developers.contentproviders"
        val uri = Uri.parse("content://" + AUTHORITY + "/" + Villains.TABLE_NAME)
        val CODE_VILLAINS_ALL = 1
        val CODE_VILLAIN_ITEM = 2
        val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(AUTHORITY, Villains.TABLE_NAME, CODE_VILLAINS_ALL)
            MATCHER.addURI(AUTHORITY, Villains.TABLE_NAME + "/*", CODE_VILLAIN_ITEM)
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // Implement this to handle requests to delete one or more rows.
        when(MATCHER.match(uri)){
           CODE_VILLAINS_ALL->{
               throw IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
           }
           CODE_VILLAIN_ITEM->{
               val count = VillainsDatabase.villainDatabaseInstance(context).villainDao()
                       .deleteById(ContentUris.parseId(uri))
               context.getContentResolver().notifyChange(uri, null)
               return count
           }
           else->{
               throw IllegalArgumentException("\"Unknown URI: \" + uri")
           }
        }
    }

    override fun getType(uri: Uri): String? {
        // at the given URI for getting MIME TYPE
        when (MATCHER.match(uri)) {
            CODE_VILLAINS_ALL ->
                return ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + Villains.TABLE_NAME
            CODE_VILLAIN_ITEM ->
                return ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + Villains.TABLE_NAME
            else ->
                throw IllegalArgumentException("Unknown URI: " + uri)
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        //for insertion of contentValues
        when (MATCHER.match(uri)) {
            CODE_VILLAINS_ALL -> {
                val id = VillainsDatabase.villainDatabaseInstance(context).villainDao()
                        .insert(Villains.fromContentValues(values as ContentValues))
                context.contentResolver.notifyChange(uri, null)
                VillainsDatabase.villainDatabaseInstance(context).close()
                return ContentUris.withAppendedId(uri, id)
            }
            CODE_VILLAIN_ITEM -> {
                throw IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri)
            }
            else -> {
                throw IllegalArgumentException("Unknown URI: " + uri);
            }
        }
    }

    override fun onCreate(): Boolean {
        // TODO: Implement this to initialize your content provider on startup.
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val code = MATCHER.match(uri)
        if (code == CODE_VILLAINS_ALL || code == CODE_VILLAIN_ITEM) {
            val villain = VillainsDatabase.villainDatabaseInstance(context).villainDao()
            if (code == CODE_VILLAINS_ALL) {
                val cursor = villain.selectAll()
                cursor.setNotificationUri(context.getContentResolver(), uri)
                return cursor
            } else {
                val cursor = villain.selectById(ContentUris.parseId(uri))
                cursor.setNotificationUri(context.getContentResolver(), uri)
                return cursor
            }
        } else {
            throw java.lang.IllegalArgumentException("Unknown URI: " + uri)
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        when (MATCHER.match(uri)) {
            CODE_VILLAINS_ALL -> {
                throw IllegalArgumentException("Invalid URI, cannot update without ID" + uri);
            }
            CODE_VILLAIN_ITEM -> {
                val villains = Villains.fromContentValues(values as ContentValues)
                villains.id = ContentUris.parseId(uri)
                val count = VillainsDatabase.villainDatabaseInstance(context).villainDao().update(villains)
                context.getContentResolver().notifyChange(uri, null)
                return count
            }
            else -> {
                throw java.lang.IllegalArgumentException("Unknown URI: " + uri)
            }
        }
    }
}
