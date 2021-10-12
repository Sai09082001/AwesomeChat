package com.example.awesomechat.ui.splash

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel() {
    val stateSplash =  SingleLiveEvent<Boolean>()
    init {
        stateSplash.value = true
    }
}