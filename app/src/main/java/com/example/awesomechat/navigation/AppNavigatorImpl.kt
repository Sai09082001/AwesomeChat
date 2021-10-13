package com.example.awesomechat.navigation

import android.os.Bundle
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseNavigatorImpl
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class AppNavigatorImpl @Inject constructor() : BaseNavigatorImpl(), AppNavigation {
    override fun openGroupToMessageDetailScreen(bundle: Bundle?) {
        openScreen(R.id.action_message_to_messageDetail,bundle)
    }

    override fun openRegisterToLoginScreen(bundle: Bundle?) {
        openScreen(R.id.action_register_to_loginFragment,bundle)
    }

    override fun openSplashToLoginScreen(bundle: Bundle? ) {
        openScreen(R.id.action_splash_to_loginFragment, bundle)
    }

    override fun openLoginToHomeScreen(bundle: Bundle?) {
        openScreen(R.id.action_login_to_homeMessageFragment, bundle)
    }

    override fun openLoginToRegisterScreen(bundle: Bundle?) {
        openScreen(R.id.action_login_to_regisFragment,bundle)
    }

    override fun openProfileToEditProfileScreen(bundle: Bundle?) {
        openScreen(R.id.action_myPage_to_editProfileFragment, bundle)
    }
}