package com.raphael.lemon.ui.terms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raphael.lemon.R
import com.raphael.lemon.ui.theme.components.HeadingTextComponent
import com.raphael.lemon.ui.theme.navigation.PostOfficeAppRouter
import com.raphael.lemon.ui.theme.navigation.Screen
import com.raphael.lemon.ui.theme.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen(isPreview: Boolean = false) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column {
            HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_header))

        }
    }

    if (!isPreview)
        SystemBackButtonHandler {
            PostOfficeAppRouter.navigateTo(Screen.SignupScreen)
        }
}

@Preview
@Composable
fun TermsAndConditionsScreenPreview() {
    TermsAndConditionsScreen(true)

}