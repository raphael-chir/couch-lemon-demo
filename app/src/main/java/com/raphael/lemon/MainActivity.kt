package com.raphael.lemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.raphael.lemon.data.DBManager
import com.raphael.lemon.ui.PostOfficeApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        DBManager.init(this, true)

        setContent {

            PostOfficeApp()
        }
    }
}
