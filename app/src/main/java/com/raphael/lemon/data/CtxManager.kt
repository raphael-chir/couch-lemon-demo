package com.raphael.lemon.data

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.couchbase.lite.Replicator
import com.raphael.lemon.data.features.UserDetails
import com.raphael.lemon.data.replicator.DefaultReplicatorServices
import java.util.Timer
import java.util.TimerTask

class CtxManager private constructor() {

    private val TAG = CtxManager::class.simpleName

    private val REPLICATOR_TAG = DefaultReplicatorServices::class.simpleName

    private var userEmail = mutableStateOf(String())

    private var replicator: Replicator? = null

    private var sessionId = mutableStateOf(String())

    companion object {
        private val instance: CtxManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { CtxManager() }
        fun get(): CtxManager {
            return instance
        }
    }

    fun getUserEmail():MutableState<String>{
        return userEmail
    }

    fun geSessionId():MutableState<String>{
        return sessionId
    }

    fun setReplicator(replicator: Replicator){
        this.replicator = replicator

        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                Log.d(REPLICATOR_TAG, replicator.status.activityLevel.name)
            }
        },2000,15000)
    }

    fun getReplicator():Replicator?{
        return this.replicator
    }

    fun clear(){
        userEmail.value = ""
        sessionId.value = ""
        this.replicator?.stop()
        replicator = null
    }
}