package com.raphael.lemon.ui.dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.raphael.lemon.data.CtxManager
import com.raphael.lemon.data.features.DefaultCouchThreadServices
import com.raphael.lemon.ui.login.LoginViewModel
import com.raphael.lemon.ui.utils.ViewEvent

class DashboardViewModel : ViewModel(), ViewEvent<DashboardViewEvent> {

    private val TAG = LoginViewModel::class.simpleName

    val dashboardViewState = mutableStateOf(DashboardViewState())

    init {

        Log.d(TAG, "Dashboard view is initiated with user : ${dashboardViewState.value.userName}")

        // Live update of user specific information
        DefaultCouchThreadServices().getLiveUserDetails(
            CtxManager.get().getUserDetails().value.email
        ) {
            dashboardViewState.value = dashboardViewState.value.copy(userName = it.toString())
        }

        // Live update of list of subscribed thread channels
        DefaultCouchThreadServices().listLiveThreadChannels { threadChannelsList ->
            Log.d(TAG,"Update thread channel ...")
            dashboardViewState.value = dashboardViewState.value.copy(couchThreadList = threadChannelsList.toMutableStateList())
        }

    }

    override fun onEvent(event: DashboardViewEvent) {
        TODO("Not yet implemented")
    }

}