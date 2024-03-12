package com.raphael.lemon.data.features

/**
 * @author Raphael CHIR
 * Functional interface related to application features
 */
interface CouchThreadServices {

    /**
     * Search user profile based on email
     * @param email
     * @return UserDetails
     */
    fun getUserDetails(email:String):UserDetails

    /**
     * Observe user details data mutation based on email
     * @param email
     * @param consume (value: String?)
     */
    fun getLiveUserDetails(email: String?, consume: (value: String?) -> Unit)

    /**
     * List all thread channel found on the application
     * @return List<ThreadChannel>
     */
    fun listThreadChannels():List<ThreadChannel>

    /**
     * Observe thread channel data mutation
     * @param consume: (threadChannels:List<ThreadChannel>)
     */
    fun listLiveThreadChannels(consume: (threadChannels:List<ThreadChannel>) -> Unit)

    fun log()
}