package com.raphael.lemon.ui.utils

interface ViewEvent<T> {

    fun onEvent(event: T): Any
}