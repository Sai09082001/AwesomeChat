package com.example.awesomechat.ui.login

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.utils.SingleLiveEvent
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel() {
    var forgotPassword = MutableLiveData<Boolean>()
    var pass_Email_Valid = MutableLiveData<Boolean>()
    val navigateLogin = SingleLiveEvent<Boolean>()
    var stateLogin = MutableLiveData<Boolean>()

    private val auth by lazy { FirebaseAuth.getInstance() }

    init {
        navigateLogin.value = checkUserValid()
    }

    fun checkUserValid(): Boolean {
        return auth.currentUser != null
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
                    Toast.makeText(context, "Network not avaible", Toast.LENGTH_SHORT).show()
                }
        } else {
            // validate sai
            Toast.makeText(context, "Email not validate", Toast.LENGTH_SHORT).show()
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
        } else if (isEmailValid(email) && password.length >= 4) {
            pass_Email_Valid.value = true
            return true
        } else
            return true
    }

    fun doForgotPassword(email: String) {

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                forgotPassword.value = task.isSuccessful
            }

    }
}
