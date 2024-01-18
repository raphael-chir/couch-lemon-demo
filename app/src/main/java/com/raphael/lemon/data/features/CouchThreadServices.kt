package com.raphael.lemon.data.features

interface CouchThreadServices {

    fun getUserDetails(email:String):UserDetails

    fun listThreadChannels():List<ThreadChannel>
}