package com.raphael.lemon.data.features

data class UserDetails(
    var email: String?, val name: String?
) {
    constructor() : this("", "")
}
