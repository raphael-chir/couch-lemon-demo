package com.raphael.lemon.ui.theme.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UserProfileMenu(
    profileIcon: ImageVector,
    toolbarViewModel: ToolbarViewModel = viewModel(),
    onProfileClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = profileIcon,
                    contentDescription = "Profile Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(50.dp)
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                DropdownMenuItem(onClick = {
                    expanded = false
                    // Do something when item clicked
                }) {
                    Text("Profile")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    // Do something when item clicked
                }) {
                    Text("Parameters")
                }
                DropdownMenuItem(onClick = {
                    expanded = false
                    // Do something when item clicked
                }) {
                    ClickableText(
                        text = AnnotatedString("Sign up"),
                        onClick = { toolbarViewModel.onEvent(ToolbarViewEvent.LogoutEvent) })
                }
            }
        }
    }
}

@Preview
@Composable
fun UserProfileMenuPreview() {
    UserProfileMenu(
        profileIcon = Icons.Filled.AccountCircle,
        onProfileClicked = {
            // Handle profile icon clicked
        }
    )
}