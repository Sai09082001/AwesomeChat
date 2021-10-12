package com.example.awesomechat.ui.register

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterViewModel : BaseViewModel(){

    val stateRegister = MutableLiveData<Boolean>()
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    fun doUserRegister(email: String, password: String) {
        // yêu cầu validate email và pass (trong sheet)
        if (validate(email, password)) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    stateRegister.value = it.isSuccessful
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

     fun setUserName(userName : String) {
        val user = auth.currentUser

        val profileUpdates =
            UserProfileChangeRequest.Builder().setDisplayName(userName).build()

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("KMFG", "setUserName: oki ")
                }
            }

    }
}
