package com.example.awesomechat.ui.homemessage.mypage

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    var uriImage = MutableLiveData<Uri>()
    var userName = MutableLiveData<String>()
    var email = MutableLiveData<String>()

     fun isDataUserChange() {
        val user = auth.currentUser ?: return
        user.let {
            // Name, email address, and profile photo Url
            userName.value = user.displayName
            email.value = user.email
            uriImage.value = user.photoUrl
            val phone = user.phoneNumber
            // Check if user's email is verified
        }
    }
}
