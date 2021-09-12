package com.example.awesomechat.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.TimeUnit

abstract class BaseViewModel : ViewModel() {
    val ex = MutableLiveData(false)

}