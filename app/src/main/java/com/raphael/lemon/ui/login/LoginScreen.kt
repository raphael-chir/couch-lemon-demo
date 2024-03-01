package com.raphael.lemon.ui.login

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Native Mobile bits"
                )

                Text(
                    color = colorResource(id = R.color.colorGray),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Couchthread"
                )
                Spacer(modifier = Modifier.width(10.dp))
            }

            Spacer(modifier = Modifier.height(80.dp))

            MyTextField(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.message),
                onTextSelected = { loginViewModel.onEvent(LoginViewEvent.SelectedEmailEvent(it)) },
                isError = false
            )

            PasswordTextField(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                onPasswordSelected = {
                    loginViewModel.onEvent(
                        LoginViewEvent.SelectedPasswordEvent(
                            it
                        )
                    )
                },
                isError = false
            )

            Spacer(modifier = Modifier.height(80.dp))

            ButtonComponent(value = stringResource(R.string.login),
                isEnabled = true,
                onRegister = { loginViewModel.onEvent(LoginViewEvent.LoginEvent) })
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}