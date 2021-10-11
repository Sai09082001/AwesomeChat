package com.example.awesomechat.di

import com.example.awesomechat.base.BaseNavigator
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class NavigationModule {

    @Binds
    @ActivityScoped
    abstract fun provideBaseNavigation(navigation: AppNavigatorImpl): BaseNavigator

    @Binds
    @ActivityScoped
    abstract fun provideAppNavigation(navigation: AppNavigatorImpl): AppNavigation

//    @Binds
//    @ActivityScoped
//    abstract fun provideDemoNavigation(navigation: AppNavigatorImpl) : LiveNavigation

}