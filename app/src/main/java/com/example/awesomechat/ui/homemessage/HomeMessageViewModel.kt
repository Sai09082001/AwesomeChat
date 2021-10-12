package com.example.awesomechat.ui.homemessage

import android.content.Context
import com.example.awesomechat.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeMessageViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): BaseViewModel(){

}
