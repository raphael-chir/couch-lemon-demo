package com.raphael.lemon.ui.dashboard

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raphael.lemon.R
import com.raphael.lemon.data.CtxManager
import com.raphael.lemon.data.DBManager
import com.raphael.lemon.ui.theme.components.HeadingTextComponent
import com.raphael.lemon.ui.theme.components.NormalTextComponent

@Composable
fun DashboardScreen(isPreview: Boolean = false, ctxManager: CtxManager = CtxManager.get()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.hello) + ctxManager.getUserDetails().value.name)
            HeadingTextComponent(value = stringResource(R.string.dashboard))

            Spacer(modifier = Modifier.height(40.dp))

            CtxManager.get().getThreadChannels().value
                .forEach { threadChannel ->
                    threadChannel.title?.let {
                        NormalTextComponent(value = it)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
        }

    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(isPreview = true)
}
