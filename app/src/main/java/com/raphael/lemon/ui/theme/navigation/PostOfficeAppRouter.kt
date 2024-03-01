package com.raphael.lemon.ui.theme.navigation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen{

    object LoginScreen : Screen()
    object DashboardScreen : Screen()
}

object PostOfficeAppRouter {

    val currentScreen:MutableState<Screen> = mutableStateOf(value = Screen.LoginScreen)

    fun navigateTo(destination: Screen){
        Log.d("NavigateTo", destination.toString())
        currentScreen.value = destination
    }
}