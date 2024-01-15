package com.raphael.lemon.ui.register

sealed class RegisterViewEvent {

    data class SelectedFirstNameEvent(val firstName: String) : RegisterViewEvent()
    data class SelectedLastNameEvent(val lastName: String) : RegisterViewEvent()
    data class SelectedEmailEvent(val email: String) : RegisterViewEvent()
    data class SelectedPasswordEvent(val password: String) : RegisterViewEvent()
    data class CheckedTermOfUse(val isChecked: Boolean) : RegisterViewEvent()
    object RegisterEvent : RegisterViewEvent()
}

