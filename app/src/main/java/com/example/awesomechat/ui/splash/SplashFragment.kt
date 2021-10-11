package com.example.awesomechat.ui.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.SplashFragmentBinding
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.utils.KeyFileShare.FILE_NAME
import com.example.awesomechat.utils.KeyFileShare.KEY_EMAIL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {

    @Inject
    lateinit var appNavigation: AppNavigation

    override fun initViews() {

      //  dataRef = FirebaseDatabase.getInstance().reference.child("Users")
        handleSplashScreen()
        //    NavHostFragment.findNavController(this).popBackStack(R.id.splashFragment, true)
    }
    fun handleSplashScreen(){
        Handler(Looper.getMainLooper()).postDelayed({
//            if (auth.currentUser!= null) {
//                appNavigation.openLoginToHomeScreen()
//            } else {
//                appNavigation.openSplashToLoginScreen()
//            }

//          mViewModel.navigateSplash.observe(this, Observer {
//              if(it){
//                  appNavigation.openSplashToLoginScreen()
//              }else{
//                  appNavigation.openSplashToLoginScreen()
//              }
//          })
            appNavigation.openSplashToLoginScreen()
        }, 2000)
    }
//    fun isExistPref(key: String?): Boolean {
//        val pref: SharedPreferences = requireContext()
//            .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
//        return pref.contains(key)
//    }

    fun getPref(key: String?): String? {
        val pref: SharedPreferences = requireContext()
            .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        return pref.getString(key, null)
    }

    override fun initBinding(mRootView: View): SplashFragmentBinding {
        return SplashFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.splash_fragment
    }
}