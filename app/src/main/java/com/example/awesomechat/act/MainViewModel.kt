package com.example.awesomechat.viewmodel.act

import android.content.Context
import com.example.awesomechat.utils.SingleLiveEvent
import com.example.awesomechat.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : BaseViewModel() {

    val actionState = SingleLiveEvent<MainActionState>()
}

sealed class MainActionState {
    object OpenLoginScreen : MainActionState()
//    class OpenLiveStream(val intentData: IntentData) : MainActionState()
}

