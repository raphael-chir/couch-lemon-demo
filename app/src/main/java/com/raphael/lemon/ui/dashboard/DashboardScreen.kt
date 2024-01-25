package com.raphael.lemon.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raphael.lemon.R
import com.raphael.lemon.data.CtxManager
import com.raphael.lemon.data.DBManager
import com.raphael.lemon.ui.theme.components.HeadingTextComponent
import com.raphael.lemon.ui.theme.components.NormalTextComponent

@Composable
fun DashboardScreen(isPreview: Boolean = false, dashboardViewModel: DashboardViewModel = viewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(28.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = stringResource(id = R.string.hello) + " " + dashboardViewModel.dashboardViewState.value.userName)
            HeadingTextComponent(value = stringResource(R.string.dashboard))

            Spacer(modifier = Modifier.height(40.dp))

            val listA = dashboardViewModel.dashboardViewState.value.couchThreadList
            //val listA = listOf<String>("Example", "Android", "Tutorial", "Jetpack", "Compose", "List", "Example","Simple", "List")
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listA){item ->
                    NormalTextComponent(value = item.title.toString())
                }
            }
            /*dashboardViewModel.dashboardViewState.value.couchThreadList
                .forEach { threadChannel ->
                    threadChannel.title?.let {
                        NormalTextComponent(value = it)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
*/
        }

    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(isPreview = true)
}
