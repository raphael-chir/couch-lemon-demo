package com.raphael.lemon.data.features

interface CouchThreadServices {

    fun getUserDetails(email:String):UserDetails

    fun getLiveUserDetails(email: String?, consume: (value: String?) -> Unit)

    fun listThreadChannels():List<ThreadChannel>
}