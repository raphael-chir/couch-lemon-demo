package com.raphael.lemon.data.appservices

import com.raphael.lemon.data.authentication.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * AppServices public API - Retrofit client interface declaration
 */
interface AppServicesPublicApi {

    /**
     * Return a session ID to interact with  app services public api
     */
    @POST("_session")
    fun authenticate(@Body user: User): Call<Object?>

    /**
     *
     */
    @DELETE("_session")
    fun disconnect(@Header("cookie") value: String): Call<Object?>
}