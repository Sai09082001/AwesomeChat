package com.example.awesomechat.ui.register

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): BaseViewModel(){

    val pass_Email_Valid = MutableLiveData<Boolean>()
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
                    Toast.makeText(context, "Network not avaible", Toast.LENGTH_SHORT).show()
                }
        } else {
            // validate sai
            Toast.makeText(context, "Email or password not avaible", Toast.LENGTH_SHORT).show()
        }
    }
    fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validate(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || password.length < 4) {
            Toast.makeText(context, "Email not validate", Toast.LENGTH_SHORT).show()
            pass_Email_Valid.value = false
            return false
        } else if (isEmailValid(email) && password.length>= 4) {
            pass_Email_Valid.value = true
            return true
        } else
            return true
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
