package com.example.awesomechat.base

import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.databinding.ActivityMainBinding
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.viewmodel.MainActionState
import com.example.awesomechat.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding , MainViewModel>() {

    private val viewModel : MainViewModel by viewModels()
    @Inject
    lateinit var appNavigation: AppNavigation

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initBinding(rootView: View): ActivityMainBinding {
        return ActivityMainBinding.bind(rootView)
    }

    override fun initViews() {
       val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as  NavHostFragment
        appNavigation.bind(navHostFragment.navController)
    }

    override fun getVM(): MainViewModel = viewModel


}