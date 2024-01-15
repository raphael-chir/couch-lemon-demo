package com.raphael.lemon.ui.login

sealed class LoginViewEvent {
    data class SelectedEmailEvent(var email:String) : LoginViewEvent()
    data class SelectedPasswordEvent(var password:String) : LoginViewEvent()
    object LoginEvent : LoginViewEvent()
}