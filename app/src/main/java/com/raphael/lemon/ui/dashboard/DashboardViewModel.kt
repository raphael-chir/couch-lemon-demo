package com.raphael.lemon.ui.dashboard

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.raphael.lemon.data.CtxManager
import com.raphael.lemon.data.features.CouchThreadServices
import com.raphael.lemon.data.features.DefaultCouchThreadServices
import com.raphael.lemon.ui.login.LoginViewModel
import com.raphael.lemon.ui.utils.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(couchThreadServices: CouchThreadServices) : ViewModel(), ViewEvent<DashboardViewEvent> {

    private val TAG = LoginViewModel::class.simpleName

    lateinit var couchThreadServices: CouchThreadServices

    val dashboardViewState = mutableStateOf(DashboardViewState())

    init {

        Log.d(TAG, "Dashboard view is initiated with user : ${CtxManager.get().getUserEmail().value}")

        // Live update of user specific information
        couchThreadServices.getLiveUserDetails(
            CtxManager.get().getUserEmail().value
        ) {
            dashboardViewState.value = dashboardViewState.value.copy(userName = it.toString())
        }

        // Live update of list of subscribed thread channels
        couchThreadServices.listLiveThreadChannels { threadChannelsList ->
            Log.d(TAG,"Update thread channel ...")
            dashboardViewState.value = dashboardViewState.value.copy(couchThreadList = threadChannelsList.toMutableStateList())
        }

    }

    override fun onEvent(event: DashboardViewEvent) {
        TODO("Not yet implemented")
    }

}