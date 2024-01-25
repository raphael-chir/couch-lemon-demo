package com.raphael.lemon.ui.dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.raphael.lemon.data.CtxManager
import com.raphael.lemon.data.features.DefaultCouchThreadServices
import com.raphael.lemon.data.features.ThreadChannel
import com.raphael.lemon.ui.login.LoginViewModel
import com.raphael.lemon.ui.utils.ViewEvent

class DashboardViewModel : ViewModel(), ViewEvent<DashboardViewEvent> {

    private val TAG = LoginViewModel::class.simpleName

    val dashboardViewState = mutableStateOf(DashboardViewState())

    init {

        Log.d(TAG, "Dashboard view is initiated with user : ${dashboardViewState.value.userName}")

        // User specific information
        DefaultCouchThreadServices().getLiveUserDetails(
            CtxManager.get().getUserDetails().value.email
        ) {
            dashboardViewState.value = dashboardViewState.value.copy(it.toString())
        }

        // Build live thread channels list
        //val threadChannelList = mutableStateListOf<ThreadChannel>()
        //val toMutableStateList = threadChannelList.toMutableStateList()
     /*   DefaultCouchThreadServices().listLiveThreadChannel { title, description ->
            Log.d(TAG,"Update thread channel ...")
            threadChannelList.add(ThreadChannel(title, description))
            dashboardViewState.value = dashboardViewState.value.copy(couchThreadList = threadChannelList)
        }*/

        DefaultCouchThreadServices().listLiveThreadChannels { threadChannelsList ->
            Log.d(TAG,"Update thread channel ...")
            dashboardViewState.value = dashboardViewState.value.copy(couchThreadList = threadChannelsList.toMutableStateList())
        }


    }

    override fun onEvent(event: DashboardViewEvent) {
        TODO("Not yet implemented")
    }

}