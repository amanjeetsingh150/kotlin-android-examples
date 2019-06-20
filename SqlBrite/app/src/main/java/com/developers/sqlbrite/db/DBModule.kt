package com.developers.sqlbrite.db

import android.app.Application
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.squareup.sqlbrite3.BriteDatabase
import com.squareup.sqlbrite3.SqlBrite
import dagger.Module
import dagger.Provides
import java.util.logging.Logger
import javax.inject.Singleton
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import io.reactivex.schedulers.Schedulers


/**
 * Created by Amanjeet Singh on 8/2/18.
 */
@Module
class DBModule(private val application: Application) {

    companion object {
        val log = Logger.getLogger(DBModule::class.java.name)
    }

    @Provides
    @Singleton
    fun providesSQLBrite(): SqlBrite {
        return SqlBrite.Builder()
                .logger { message -> log.info(message) }
                .build()
    }

    @Provides
    @Singleton
    fun providesDataBase(sqlBrite: SqlBrite): BriteDatabase {
        val configuration = SupportSQLiteOpenHelper.Configuration.builder(application)
                .name("repo.db")
                .callback(RepoDB())
                .build()
        val factory = FrameworkSQLiteOpenHelperFactory()
        val helper = factory.create(configuration)
        val db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io())
        db.setLoggingEnabled(true)
        return db
    }

}