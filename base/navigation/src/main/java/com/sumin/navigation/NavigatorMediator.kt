package com.sumin.navigation

import javax.inject.Inject

class NavigatorMediator @Inject constructor(private val navigator: Navigator) {

    fun navigate(route: Route) {
        navigator.navigate(route)
    }
}