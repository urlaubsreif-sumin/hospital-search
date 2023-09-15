package com.sumin.hospital

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sumin.navigation.Navigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigatorModule {

    @Binds
    abstract fun bindNavigator(impl: NavigatorImpl): Navigator
}