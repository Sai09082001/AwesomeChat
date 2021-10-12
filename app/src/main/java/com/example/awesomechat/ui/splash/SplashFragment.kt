package com.example.awesomechat.ui.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.SplashFragmentBinding
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.ui.homemessage.HomeMessageViewModel
import com.example.awesomechat.utils.KeyFileShare.FILE_NAME
import com.example.awesomechat.utils.KeyFileShare.KEY_EMAIL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding>() {

    @Inject
    lateinit var appNavigation: AppNavigation

    override fun initViews() {
        handleSplashScreen()
    }
    fun handleSplashScreen(){
        Handler(Looper.getMainLooper()).postDelayed({
            appNavigation.openSplashToLoginScreen()
        }, 2000)
    }

    fun getPref(key: String?): String? {
        val pref: SharedPreferences = requireContext()
            .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return pref.getString(key, null)
    }

    override fun initBinding(mRootView: View): SplashFragmentBinding {
        return SplashFragmentBinding.bind(mRootView)
    }

    override fun getLayoutId(): Int {
        return R.layout.splash_fragment
    }

    override fun getVM(): ViewModel {
        TODO("Not yet implemented")
    }

}