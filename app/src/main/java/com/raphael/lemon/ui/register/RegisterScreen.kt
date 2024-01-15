package com.raphael.lemon.ui.register

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
import com.raphael.lemon.ui.theme.components.CheckBoxComponent
import com.raphael.lemon.ui.theme.components.ClickableLoginTextComponent
import com.raphael.lemon.ui.theme.components.DividerTextComponent
import com.raphael.lemon.ui.theme.components.HeadingTextComponent
import com.raphael.lemon.ui.theme.components.MyTextField
import com.raphael.lemon.ui.theme.components.NormalTextComponent
import com.raphael.lemon.ui.theme.components.PasswordTextField
import com.raphael.lemon.ui.theme.navigation.PostOfficeAppRouter
import com.raphael.lemon.ui.theme.navigation.Screen
import com.raphael.lemon.ui.theme.navigation.SystemBackButtonHandler
import com.raphael.lemon.R

@Composable
fun RegisterScreen(isPreview: Boolean = false, registerViewModel: RegisterViewModel = viewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            NormalTextComponent(value = stringResource(id = R.string.hello))

            HeadingTextComponent(value = stringResource(id = R.string.create_account))

            Spacer(modifier = Modifier.height(20.dp))

            MyTextField(
                labelValue = stringResource(id = R.string.first_name),
                painterResource = painterResource(id = R.drawable.profile),
                onTextSelected = {
                    registerViewModel.onEvent(RegisterViewEvent.SelectedFirstNameEvent(it))
                },
                isError = !registerViewModel.registerViewState.value.isFirstNameValid
            )

            MyTextField(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.profile),
                onTextSelected = {
                    registerViewModel.onEvent(RegisterViewEvent.SelectedLastNameEvent(it))
                },
                isError = !registerViewModel.registerViewState.value.isLastNameValid
            )

            MyTextField(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.message),
                onTextSelected = {
                    registerViewModel.onEvent(RegisterViewEvent.SelectedEmailEvent(it))
                },
                isError = !registerViewModel.registerViewState.value.isEmailValid
            )

            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_lock),
                onPasswordSelected = {
                    registerViewModel.onEvent(RegisterViewEvent.SelectedPasswordEvent(it))
                },
                isError = !registerViewModel.registerViewState.value.isPasswordValid
            )

            CheckBoxComponent(
                value = stringResource(id = R.string.terms_and_condition),
                onTextSelected = {
                    PostOfficeAppRouter.navigateTo(Screen.TermOfUseScreen)
                },
                onCheckedCondition = {
                    registerViewModel.onEvent(RegisterViewEvent.CheckedTermOfUse(it))
                }
            )

            Spacer(modifier = Modifier.height(60.dp))

            ButtonComponent(value = stringResource(id = R.string.register),
                            isEnabled = registerViewModel.isValid() ,
                            onRegister = {registerViewModel.onEvent(RegisterViewEvent.RegisterEvent)})

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(loginScreen = false, onTextSelected = {
                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
            })

        }

    }
    if (!isPreview) SystemBackButtonHandler {
        PostOfficeAppRouter.navigateTo(Screen.SignupScreen)
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(true)
}