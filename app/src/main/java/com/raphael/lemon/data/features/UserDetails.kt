package com.raphael.lemon.data.features

data class UserDetails(
    val email: String?, val name: String?
) {
    constructor() : this("", "")
}
