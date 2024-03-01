package com.raphael.lemon.ui.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raphael.lemon.R
import com.raphael.lemon.data.features.ThreadChannel
import com.raphael.lemon.ui.theme.components.HeadingTextComponent
import com.raphael.lemon.ui.theme.components.NormalTextComponent
import com.raphael.lemon.ui.theme.toolbar.UserProfileMenu

@Composable
fun DashboardScreen(
    dashboardViewModel: DashboardViewModel = viewModel()
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp),
    ) {
        UserProfileMenu(
            profileIcon = Icons.Filled.AccountCircle,
            onProfileClicked = {
                // Handle profile icon clicked
            }
        )
        Column() {
            Spacer(modifier = Modifier.height(80.dp))
            NormalTextComponent(value = stringResource(id = R.string.hello) + " " + dashboardViewModel.dashboardViewState.value.userName)
            HeadingTextComponent(value = stringResource(R.string.dashboard))

            Spacer(modifier = Modifier.height(60.dp))

            val listA = dashboardViewModel.dashboardViewState.value.couchThreadList

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listA) { item ->
                    NormalTextComponent(value = item.title.toString())
                }
            }
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun DashboardScreenPreview() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp),
    ) {

        UserProfileMenu(
            profileIcon = Icons.Filled.AccountCircle,
            onProfileClicked = {
                // Handle profile icon clicked
            }
        )

        Column {

            Spacer(modifier = Modifier.height(80.dp))
            NormalTextComponent(value = stringResource(id = R.string.hello) + " " + "Michel")
            HeadingTextComponent(value = stringResource(R.string.dashboard))
            val listA = listOf<ThreadChannel>(
                ThreadChannel("Mobile Workshop", ""),
                ThreadChannel("How to dev with Kotlin", "")
            )
            Spacer(modifier = Modifier.height(60.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listA) { item ->
                    NormalTextComponent(value = item.title.toString())
                }
            }
        }
    }


}
