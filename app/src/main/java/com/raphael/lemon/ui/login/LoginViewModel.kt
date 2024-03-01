package com.raphael.lemon.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.raphael.lemon.data.CtxManager
import com.raphael.lemon.data.authentication.AppServicesAuth
import com.raphael.lemon.data.replicator.DefaultReplicatorServices
import com.raphael.lemon.data.features.DefaultCouchThreadServices
import com.raphael.lemon.ui.theme.navigation.PostOfficeAppRouter
import com.raphael.lemon.ui.theme.navigation.Screen
import com.raphael.lemon.ui.utils.ViewEvent

class LoginViewModel : ViewModel(), ViewEvent<LoginViewEvent> {

    private val TAG = LoginViewModel::class.simpleName

    val loginViewState = mutableStateOf(LoginViewState())

    override fun onEvent(event: LoginViewEvent) = when (event) {

        is LoginViewEvent.SelectedEmailEvent -> {
            loginViewState.value = loginViewState.value.copy(email = event.email)
        }

        is LoginViewEvent.SelectedPasswordEvent -> {
            loginViewState.value = loginViewState.value.copy(password = event.password)
        }

        is LoginViewEvent.LoginEvent -> {
            Log.d(TAG, "Entering into Login process ...")
            var email = loginViewState.value.email
            var password = loginViewState.value.password
            Log.d(TAG, "Try to authenticate $email  / $password ")

            AppServicesAuth().authenticate(
                email, password,
                onResponse = { call, response ->
                    if (response.isSuccessful) {
                        Log.d(TAG, "Successfully connected $email !")

                        DefaultReplicatorServices().start(email, password)

                        val userDetails = DefaultCouchThreadServices().getUserDetails(email)
                        CtxManager.get().getUserDetails().value = userDetails

                        var sessionInfos = response.headers().get("set-cookie").toString()
                        sessionInfos= sessionInfos.split(";")[0]
                        sessionInfos = sessionInfos.split("=")[1]
                        CtxManager.get().geSessionId().value = sessionInfos

                        this.clear()
                        PostOfficeAppRouter.navigateTo(Screen.DashboardScreen)
                    } else {
                        Log.d(TAG, "The response is unsuccessful du to code ${response.code()}")
                        Log.d(TAG, "Request URL : ${call.request().url()}")
                        Log.d(TAG, "Request headers : ${call.request().headers()}")
                        Log.d(TAG, "Request body : ${call.request().body()}")
                        // TODO auth error 1
                    }
                },
                onFailure = { call, t ->
                    Log.d(TAG, "Handling the failure ...")
                    Log.d(TAG, "Cause ${t.cause}")
                    // TODO auth error 2
                })
        }

        else -> {}
    }

    fun clear(){
        loginViewState.value = LoginViewState()
    }
}