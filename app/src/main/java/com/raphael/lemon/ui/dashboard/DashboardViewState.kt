package com.raphael.lemon.ui.dashboard

import com.raphael.lemon.data.features.ThreadChannel

data class DashboardViewState(
    var userName:String = "",
    var couchThreadList:MutableList<ThreadChannel> = arrayListOf<ThreadChannel>()
)
