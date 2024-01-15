package com.raphael.lemon.ui.register

data class RegisterViewState(
    var firstName: String = "",
    var isFirstNameValid: Boolean = false,
    var lastName: String = "",
    var isLastNameValid: Boolean = false,
    var email: String = "",
    var isEmailValid: Boolean = false,
    var password: String = "",
    var isPasswordValid: Boolean = false,
    var isPrivacyPolicyAccepted: Boolean = false
)
