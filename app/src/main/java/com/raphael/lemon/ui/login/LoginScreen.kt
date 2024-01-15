package com.raphael.lemon.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raphael.lemon.ui.theme.components.ButtonComponent
import com.raphael.lemon.ui.theme.components.ClickableLoginTextComponent
import com.raphael.lemon.ui.theme.components.DividerTextComponent
import com.raphael.lemon.ui.theme.components.HeadingTextComponent
import com.raphael.lemon.ui.theme.components.MyTextField
import com.raphael.lemon.ui.theme.components.NormalTextComponent
import com.raphael.lemon.ui.theme.components.PasswordTextField
import com.raphael.lemon.ui.theme.components.UnderlinedTextComponent
import com.raphael.lemon.ui.theme.navigation.PostOfficeAppRouter
import com.raphael.lemon.ui.theme.navigation.Screen
import com.raphael.lemon.ui.theme.navigation.SystemBackButtonHandler
import com.raphael.lemon.R

@Composable
fun LoginScreen(isPreview:Boolean = false, loginViewModel: LoginViewModel = viewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(R.string.welcome))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message),
                onTextSelected = {loginViewModel.onEvent(LoginViewEvent.SelectedEmailEvent(it))},
                isError = false
            )
            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                onPasswordSelected = {loginViewModel.onEvent(LoginViewEvent.SelectedPasswordEvent(it))},
                isError = false
            )
            Spacer(modifier = Modifier.height(40.dp))

            UnderlinedTextComponent(value = stringResource(R.string.forgot_your_password))

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(R.string.login),
                            isEnabled = true,
                            onRegister = {loginViewModel.onEvent(LoginViewEvent.LoginEvent)})

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(loginScreen = true, onTextSelected = {
                PostOfficeAppRouter.navigateTo(Screen.SignupScreen)
            })
        }
    }

    if(!isPreview)
        SystemBackButtonHandler{
            PostOfficeAppRouter.navigateTo(Screen.SignupScreen)
        }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(isPreview = true)
}