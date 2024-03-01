package com.raphael.lemon.data.authentication

import retrofit2.Call
import retrofit2.Response

interface AuthServices {

    fun authenticate(
        login: String,
        password: String,
        onResponse: (call: Call<Object?>, response: Response<Object?>) -> Unit,
        onFailure: (call: Call<Object?>, t: Throwable) -> Unit
    )

    fun disconnect(
        sessionId: String,
        onResponse: (call: Call<Object?>, response: Response<Object?>) -> Unit,
        onFailure: (call: Call<Object?>, t: Throwable) -> Unit
    )
}