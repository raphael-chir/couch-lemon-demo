package com.raphael.lemon.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.raphael.lemon.data.features.ThreadChannel
import com.raphael.lemon.data.features.UserDetails

class CtxManager private constructor() {

    //private var threadChannels = mutableStateOf<ArrayList<ThreadChannel>>(arrayListOf())

    private var threadChannels = mutableStateOf<List<ThreadChannel>>(arrayListOf())

    private var userDetails = mutableStateOf(UserDetails())

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
}