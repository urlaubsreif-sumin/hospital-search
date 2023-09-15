package com.sumin.hospital

import com.sumin.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigatorModule {

    @Binds
    abstract fun bindNavigator(impl: NavigatorImpl): Navigator
}