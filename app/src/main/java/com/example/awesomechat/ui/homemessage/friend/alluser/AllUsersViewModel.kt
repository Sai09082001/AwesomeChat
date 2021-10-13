package com.example.awesomechat.ui.homemessage.friend.alluser

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    val listUsers = MutableLiveData<ArrayList<Users>>()
    private val auth by lazy { FirebaseAuth.getInstance() }

    fun loadAllUsers() {
        val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
            .child(auth.currentUser!!.displayName.toString())
        dataRef.addValueEventListener(object : ValueEventListener {
               override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val list = arrayListOf<Users>()
                    for (postSnapshot in dataSnapshot.children) {
                         val name = postSnapshot.child("name").getValue()
                         val profileImage = postSnapshot.child("profileImage").getValue()
                         val isFriend = postSnapshot.child("isFriend").getValue()
                         val user = Users(name.toString(), profileImage.toString(),isFriend.toString())
                         list.add(user)
                    }
                    listUsers.value = list

               }

               override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                    // ...
               }
          })
     }

}
