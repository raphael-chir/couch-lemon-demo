package com.raphael.lemon.data

import android.content.Context
import android.util.Log
import com.couchbase.lite.CouchbaseLite
import com.couchbase.lite.Database


class DBManager private constructor(context: Context, debug: Boolean) {

    companion object {
        //@Volatile
        private var instance: DBManager? = null

        private val TAG = DBManager::class.simpleName

        fun init(context: Context, debug: Boolean): DBManager? =
            instance ?: synchronized(this) {
                Log.d(TAG, "create singleton")
                instance ?: DBManager(context, debug).also { instance = it }
                instance?.init(context, debug)
                return instance
            }

        fun getInstance(): DBManager? {
            Log.d(TAG, "This is DBManager instance $instance")
            return instance
        }
    }

    private val TAG = DBManager::class.simpleName

    private lateinit var database: Database

    private fun init(context: Context, debug: Boolean) {
        CouchbaseLite.init(context, debug)
        Log.d(TAG, "CBL Initialized")
    }

    // Get a database by dbName, if not exist it will be created
    public fun get(dbName: String): Database {
        database = Database(dbName)
        Log.i(TAG, "Database created $database")
        return database
    }

}