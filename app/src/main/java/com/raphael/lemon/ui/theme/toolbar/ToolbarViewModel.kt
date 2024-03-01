package com.raphael.lemon.ui.theme.toolbar

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.raphael.lemon.data.CtxManager
import com.raphael.lemon.data.authentication.AppServicesAuth
import com.raphael.lemon.ui.theme.navigation.PostOfficeAppRouter
import com.raphael.lemon.ui.theme.navigation.Screen
import com.raphael.lemon.ui.utils.ViewEvent

class ToolbarViewModel : ViewModel(), ViewEvent<ToolbarViewEvent> {

    private val TAG = ToolbarViewModel::class.simpleName

    val toolbarViewState = mutableStateOf(ToolbarViewState())

    override fun onEvent(event: ToolbarViewEvent) = when (event) {

        is ToolbarViewEvent.LogoutEvent -> {
            Log.d(TAG, "Entering into Logout process ...")
            AppServicesAuth().disconnect(CtxManager.get().geSessionId().value,
                onResponse = { call, response ->
                    CtxManager.get().clear()
                    PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
                },
                onFailure = { call, failure ->
                    Log.d(TAG, "Unable to close session")
                    PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
                }
            )
        }

        else -> {}
    }
}