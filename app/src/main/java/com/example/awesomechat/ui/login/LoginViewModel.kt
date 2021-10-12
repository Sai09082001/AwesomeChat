package com.example.awesomechat.ui.login

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.utils.SingleLiveEvent
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(): BaseViewModel(){

    val navigateLogin = SingleLiveEvent<Boolean>()
    val stateLogin = MutableLiveData<Boolean>()

    private val auth by lazy { FirebaseAuth.getInstance() }

    init {
        navigateLogin.value = checkUserValid()
    }

    fun checkUserValid() : Boolean{
        if(auth.currentUser != null){
            return  true
        }else return false
    }
     fun doUserLogin(email: String, password: String) {
         // yêu cầu validate email và pass (trong sheet)
         if (validate(email, password)) {
             auth.signInWithEmailAndPassword(email, password)
                 .addOnCompleteListener {
                     stateLogin.value = it.isSuccessful
                 }
                 .addOnFailureListener {
                     // xử lý nếu email, password sai, hoặc mất mạng
                     Log.i("KMFG", "Network is avaible")
                 }
         } else {
             // validate sai
         }
    }

    fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validate(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()){
            return false
        }
        else if(isEmailValid(email)){
            return true
        }
        else return true
    }
}
