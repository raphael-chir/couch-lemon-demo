package com.raphael.lemon.data.features

/**
 * @author Raphael CHIR
 *
 * Immutable UserDetails bean
 */
data class UserDetails(
    var email: String?, var name: String?
) {
    constructor() : this("", "")
}
