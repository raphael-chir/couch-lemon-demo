package com.raphael.lemon.data

import android.content.Context
import android.util.Log
import com.couchbase.lite.CouchbaseLite
import com.couchbase.lite.Database
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 */
@Singleton
class DBManager @Inject constructor(context: Context, debug: Boolean){

    private val tag = DBManager::class.simpleName

    private lateinit var database: Database

    // CouchbaseLite initialization
    init {
        Log.d(tag, "DBManager Singleton Creation")
        CouchbaseLite.init(context, debug)
        Log.d(tag, "CBL Initialized with DI")
    }

    // Get a database by dbName, if not exist it will be created
    fun get(dbName: String): Database {
        database = Database(dbName)
        Log.d(tag, "Database created $database")
        return database
    }
}