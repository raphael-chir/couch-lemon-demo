package com.raphael.lemon.data

import android.os.Handler
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.couchbase.lite.Replicator
import com.raphael.lemon.data.features.ThreadChannel
import com.raphael.lemon.data.features.UserDetails
import com.raphael.lemon.data.replicator.DefaultReplicatorServices
import java.util.Timer
import java.util.TimerTask

class CtxManager private constructor() {

    private val TAG = CtxManager::class.simpleName

    private val REPLICATOR_TAG = DefaultReplicatorServices::class.simpleName

    private var threadChannels = mutableStateOf<List<ThreadChannel>>(arrayListOf())

    private var userDetails = mutableStateOf(UserDetails())

    private var replicator: Replicator? = null

    companion object {
        val instance: CtxManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { CtxManager() }
        fun get(): CtxManager {
            return instance
        }
    }

    fun getUserDetails():MutableState<UserDetails>{
        return userDetails
    }

    fun getThreadChannels():MutableState<List<ThreadChannel>>{
        return threadChannels
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
}