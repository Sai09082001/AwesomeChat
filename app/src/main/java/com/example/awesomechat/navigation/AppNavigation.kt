package com.example.awesomechat.navigation

import android.os.Bundle
import com.example.awesomechat.base.BaseNavigator

interface AppNavigation : BaseNavigator{

    fun openSplashToLoginScreen( bundle: Bundle ?= null)
    fun openLoginToHomeScreen( bundle: Bundle ?= null)
    fun openLoginToRegisterScreen( bundle: Bundle ?= null)
}