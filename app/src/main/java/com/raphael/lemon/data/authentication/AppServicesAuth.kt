package com.raphael.lemon.data.authentication

import com.raphael.lemon.data.appservices.RestClient
import com.raphael.lemon.ui.login.LoginViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Warning : This is just a trick to perform a quick authentication process using AppServices.
 * Note that this is not the purpose of App Services, you must use an external authentication services
 * such as (custom basic auth, Oauth, OIDC, ...)
 */
class AppServicesAuth : AuthServices {

    private val TAG = LoginViewModel::class.simpleName

    override fun authenticate(
        login: String,
        password: String,
        onResponse: (call: Call<Object?>, response: Response<Object?>) -> Unit,
        onFailure: (call: Call<Object?>, t: Throwable) -> Unit
    ) {

        val call = RestClient.appServicesPublicApi.authenticate(User(login, password))

        call.enqueue(object : Callback<Object?> {
            override fun onResponse(call: Call<Object?>, response: Response<Object?>) {
                onResponse(call, response)
            }

            override fun onFailure(call: Call<Object?>, t: Throwable) {
                onFailure(call, t)
            }
        })
    }

    override fun disconnect(
        sessionId: String,
        onResponse: (call: Call<Object?>, response: Response<Object?>) -> Unit,
        onFailure: (call: Call<Object?>, t: Throwable) -> Unit
    ) {
        val call = RestClient.appServicesPublicApi.disconnect(sessionId)

        call.enqueue(object : Callback<Object?> {
            override fun onResponse(call: Call<Object?>, response: Response<Object?>) {
                onResponse(call, response)
            }

            override fun onFailure(call: Call<Object?>, t: Throwable) {
                onFailure(call, t)
            }
        })

    }

}