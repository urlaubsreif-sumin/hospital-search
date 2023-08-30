package com.sumin.navigation

import android.util.Log

object NavigatorMediator {
    private var navigator: Navigator? = null

    fun connect(navigator: Navigator) {
        this.navigator = navigator
    }

    fun disconnect() {
        this.navigator = null
    }

    fun navigate(route: Route) {
        Log.i("[navigate test]", "NavigatorMediator.navigate -> ${route.toString()}")
        navigator?.navigate(route)
    }
}