package com.example.awesomechat.navigation

import android.os.Bundle
import com.example.awesomechat.base.BaseNavigator

interface AppNavigation : BaseNavigator{

    fun openLoginScreen( bundle: Bundle ?= null)
}