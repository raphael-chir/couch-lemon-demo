package com.raphael.lemon.data.replicator

import android.util.Log
import com.couchbase.lite.BasicAuthenticator
import com.couchbase.lite.CouchbaseLiteException
import com.couchbase.lite.Replicator
import com.couchbase.lite.ReplicatorConfigurationFactory
import com.couchbase.lite.ReplicatorType
import com.couchbase.lite.URLEndpoint
import com.couchbase.lite.newConfig
import com.raphael.lemon.data.Config
import com.raphael.lemon.data.CtxManager
import com.raphael.lemon.data.DBManager
import com.raphael.lemon.ui.login.LoginViewModel
import java.net.URI

/**
 * Here is a minimalistic default implementation of ReplicatorServices
 */
class DefaultReplicatorServices:ReplicatorServices {

    private val TAG = DefaultReplicatorServices::class.simpleName

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
            
            change.status.progress?.let{
                Log.d(TAG, "Total number of changes to be processed: ${it.total}")
                Log.d(TAG, "Number of completed changes processed: ${it.completed}")
            }
            
            change.status.activityLevel?.let {
                Log.d(TAG, "Replicator activity level is ${it.name}")
            }

            val pendingDocumentIds = replicator.getPendingDocumentIds()
            Log.d(TAG, "Set of pending documents by ids : ${pendingDocumentIds.size}" )

        }
        replicator.start(false)
        Log.d(TAG, "Starting Replicator ...")
        CtxManager.get().setReplicator(replicator)
    }
}