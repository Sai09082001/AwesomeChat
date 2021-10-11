package com.example.awesomechat.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.utils.SingleLiveEvent
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): BaseViewModel(){

    val navigateLogin = SingleLiveEvent<Boolean>()
    val stateLogin = MutableLiveData<Boolean>()

    fun setValue() {
        navigateLogin.call()
    }
    fun handleLogin() {
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            navigateLogin.value = true
//                gotoHomeMessage(
        } else {
//                gotoLoginScreen()
            navigateLogin.value = false
        }

    }

     fun doUserLogin(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
            OnCompleteListener {
                if (it.isSuccessful) {
                    stateLogin.value = true
                }else {
                    stateLogin.value = false
                }
            })
    }
}
