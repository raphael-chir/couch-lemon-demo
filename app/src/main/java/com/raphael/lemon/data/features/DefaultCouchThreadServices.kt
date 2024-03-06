package com.raphael.lemon.data.features

import android.util.Log
import com.couchbase.lite.Parameters
import com.raphael.lemon.data.Config
import com.raphael.lemon.data.DBManager

/**
 * @author Raphael CHIR
 *
 * Default implementation of CouchThreadServices interface
 */
class DefaultCouchThreadServices : CouchThreadServices {

    private val TAG = DefaultCouchThreadServices::class.simpleName

    override fun getUserDetails(email: String): UserDetails {
        val database = DBManager.getInstance()?.get(Config.DB_NAME)
        val userDetails: UserDetails
        val query = database?.createQuery("select * from _ where type=\$pType and email=\$pEmail")
        val parameters = Parameters()
        parameters.setValue("pType", "user-details")
        parameters.setValue("pEmail", email)
        query?.parameters = parameters

        query?.execute().use { resultSet ->
            val next = resultSet?.next()
            userDetails = UserDetails(
                next?.getDictionary(0)?.getString("email"),
                next?.getDictionary(0)?.getString("name")
            )
        }

        return userDetails
    }

    override fun getLiveUserDetails(email: String?, consume: (value: String?) -> Unit) {
        val database = DBManager.getInstance()?.get(Config.DB_NAME)
        val userDetails: UserDetails
        val query = database?.createQuery("select * from _ where type=\$pType and email=\$pEmail")
        val parameters = Parameters()
        parameters.setValue("pType", "user-details")
        parameters.setValue("pEmail", email)
        query?.parameters = parameters

        val token = query!!.addChangeListener { change ->
            change.results?.let { rs ->
                rs.forEach {
                    consume(it?.getDictionary(0)?.getString("name"))
                }
            }
        }
    }

    override fun listThreadChannels(): List<ThreadChannel> {

        val database = DBManager.getInstance()?.get(Config.DB_NAME)
        val threadChannels = mutableListOf<ThreadChannel>()
        val query = database?.createQuery("select * from _ where type=\"channel\"")
        val resultSet = query?.execute().use { resultSet ->
            resultSet?.allResults()?.forEach {
                threadChannels.add(
                    ThreadChannel(
                        title = it.getDictionary(0)?.getString("title"),
                        description = it.getDictionary(0)?.getString("description")
                    )
                )
            }
        }
        Log.d(TAG, "${threadChannels.size} channels received")
        return threadChannels
    }

    override fun listLiveThreadChannels(consume: (threadChannels:List<ThreadChannel>) -> Unit) {
        val database = DBManager.getInstance()?.get(Config.DB_NAME)
        val query = database?.createQuery("select * from _ where type=\"thread-channel\"")
        val threadChannels = arrayListOf<ThreadChannel>()
        // Adds a query change listener.
        // Changes will be posted on the main queue.
        val token = query!!.addChangeListener { change ->
            change.results?.let { rs ->
                threadChannels.clear()
                rs.forEach {
                    Log.d(TAG, "results: ${it.getDictionary(0)?.getString("title")}")
                    threadChannels.add(
                        ThreadChannel(
                            title = it.getDictionary(0)?.getString("title"),
                            description = it.getDictionary(0)?.getString("description")
                        )
                    )
                }
                consume(threadChannels)
            }
        }
    }
}