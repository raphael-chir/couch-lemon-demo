package com.raphael.lemon.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.raphael.lemon.ui.theme.navigation.PostOfficeAppRouter
import com.raphael.lemon.ui.theme.navigation.Screen
import com.raphael.lemon.ui.login.LoginScreen
import com.raphael.lemon.ui.register.RegisterScreen
import com.raphael.lemon.ui.terms.TermsAndConditionsScreen

@Composable
fun PostOfficeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Crossfade(targetState = PostOfficeAppRouter.currentScreen, label = "")
        { currentState ->
            when (currentState.value) {
                is Screen.SignupScreen -> {
                    RegisterScreen()
                }

                is Screen.TermOfUseScreen -> {
                    TermsAndConditionsScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }
            }
        }
    }
}