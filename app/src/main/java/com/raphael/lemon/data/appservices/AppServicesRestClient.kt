package com.raphael.lemon.data.appservices

import com.raphael.lemon.data.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit Client configuration
 */
object AppServicesRetrofitClient {
    private const val BASE_URL = Config.APPSERVICES_REST_BASE_URL

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object RestClient {
    val appServicesPublicApi: AppServicesPublicApi by lazy {
        AppServicesRetrofitClient.retrofit.create(AppServicesPublicApi::class.java)
    }
}