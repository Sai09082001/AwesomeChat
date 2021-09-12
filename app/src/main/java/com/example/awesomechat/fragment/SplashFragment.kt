package com.example.awesomechat.fragment

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.awesomechat.R
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.databinding.SplashFragmentBinding
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {
    @Inject
    lateinit var appNavigation: AppNavigation
    override fun initViews() {
        Handler(Looper.getMainLooper()).postDelayed({
            gotoLoginScreen()
        }, 2000)
        NavHostFragment.findNavController(this).popBackStack(R.id.splashFragment, true)
    }

    private fun gotoLoginScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
    }

    override fun initBinding(mRootView: View): SplashFragmentBinding? {
       return SplashFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.splash_fragment
    }
}