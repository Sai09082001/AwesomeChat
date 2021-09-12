package com.example.awesomechat.base

import android.view.View
import com.example.awesomechat.R
import com.example.awesomechat.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initBinding(rootView: View): ActivityMainBinding {
        return ActivityMainBinding.bind(rootView)
    }

    override fun initViews() {

    }
}