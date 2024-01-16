package com.raphael.lemon.data.replicator

import android.util.Log
import com.couchbase.lite.BasicAuthenticator
import com.couchbase.lite.Replicator
import com.couchbase.lite.ReplicatorConfigurationFactory
import com.couchbase.lite.ReplicatorType
import com.couchbase.lite.URLEndpoint
import com.couchbase.lite.newConfig
import com.raphael.lemon.data.Config
import com.raphael.lemon.data.DBManager
import com.raphael.lemon.ui.login.LoginViewModel
import java.net.URI

/**
 * Here is a minimalistic default implementation of ReplicatorServices
 */
class DefaultReplicatorServices:ReplicatorServices {

    private val TAG = LoginViewModel::class.simpleName

    override fun start(login: String, password: String) {
        val replicator =
            Replicator(
                ReplicatorConfigurationFactory.newConfig(
                    collections = mapOf(
                        DBManager.getInstance()!!.get("couchthread").collections to null
                    ),
                    target = URLEndpoint(URI(Config.SYNC_GATEWAY_BASE_URL)),
                    type = ReplicatorType.PUSH_AND_PULL,
                    authenticator = BasicAuthenticator(login, password.toCharArray()),
                    continuous = true
                )
            )

        replicator.addChangeListener { change ->
            change.status.error?.let {
                Log.d(TAG, "Error code: ${it.code}")
            }
        }
        replicator.start()
        Log.d(TAG, "Starting Replicator ...")
    }
}