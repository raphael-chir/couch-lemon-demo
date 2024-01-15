package com.raphael.lemon.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.couchbase.lite.BasicAuthenticator
import com.couchbase.lite.Replicator
import com.couchbase.lite.ReplicatorConfigurationFactory
import com.couchbase.lite.ReplicatorType
import com.couchbase.lite.URLEndpoint
import com.couchbase.lite.newConfig
import com.raphael.lemon.data.Config
import com.raphael.lemon.data.appservices.RestClient
import com.raphael.lemon.data.DBManager
import com.raphael.lemon.data.authentication.AppServicesAuth
import com.raphael.lemon.data.authentication.User
import com.raphael.lemon.data.replicator.DefaultReplicatorServices
import com.raphael.lemon.ui.utils.ViewEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URI

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
                        val post = response.body()
                        Log.d(TAG, "Successfully connected $email !")
                        DefaultReplicatorServices().start(email, password)
                        // TODO Go to home page
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
                    // TODO auth error 2
                })
        }

        else -> {}
    }

    private fun startReplicator(email: String, password: String) {
        val replicator =
            Replicator(
                ReplicatorConfigurationFactory.newConfig(
                    collections = mapOf(
                        DBManager.getInstance()?.get("Offices")!!.collections to null
                    ),
                    target = URLEndpoint(URI(Config.SYNC_GATEWAY_BASE_URL)),
                    type = ReplicatorType.PUSH_AND_PULL,
                    authenticator = BasicAuthenticator(email, password.toCharArray())
                )
            )

        replicator.addChangeListener { change ->
            change.status.error?.let {
                Log.d(TAG, "Error code: ${it.code}")
            }
        }
        replicator.start()
    }

    private fun publicAPILogin(email: String, password: String) {
        val call = RestClient.appServicesPublicApi.authenticate(User(email, password))

        call.enqueue(object : Callback<Object?> {
            override fun onResponse(call: Call<Object?>, response: Response<Object?>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    Log.d(TAG, "Successfully connected $email !")
                } else {
                    Log.d(TAG, "The response is unsuccessful du to code ${response.code()}")
                    Log.d(TAG, "Request URL : ${call.request().url()}")
                    Log.d(TAG, "Request headers : ${call.request().headers()}")
                    Log.d(TAG, "Request body : ${call.request().body()}")
                }
            }

            override fun onFailure(call: Call<Object?>, t: Throwable) {
                Log.d(TAG, "Handling the failure ...")
            }
        })
    }


}