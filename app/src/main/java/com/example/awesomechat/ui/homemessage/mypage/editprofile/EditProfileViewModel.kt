package com.example.awesomechat.ui.homemessage.mypage.editprofile

import android.content.Context
import android.net.Uri
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
class EditProfileViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    val isDataAvaible = MutableLiveData<Boolean>()

    fun checkDataEdit(uriProfile: Uri,userName: String,email: String, date: String) {
        isDataAvaible.value =
            !(uriProfile == null && userName.isEmpty() && email.isEmpty() && date.isEmpty() && !isEmailValid(email))
    }

    fun isEmailValid(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

     fun updateDataUser(uriProfile: Uri, userName: String, phone: String, date: String) {
        val user = auth.currentUser!!

        val profileUpdates =
            UserProfileChangeRequest.Builder().setDisplayName(userName)
                .setPhotoUri(uriProfile).build()

        user.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }
            }

    }
}
