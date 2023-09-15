package com.sumin.navigation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class NavigatorMediatorModule {

    @Provides
    fun provideNavigatorMediator(navigator: Navigator): NavigatorMediator {
        return NavigatorMediator(navigator)
    }
}