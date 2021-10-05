package com.example.awesomechat.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.awesomechat.model.Users
import com.google.firebase.database.*
import java.util.concurrent.TimeUnit

abstract class BaseViewModel : ViewModel() {
    var listUsers : MutableLiveData<ArrayList<Users>> = MutableLiveData()
    private lateinit var dataRef : DatabaseReference
    fun loadAllUsers() {
        dataRef = FirebaseDatabase.getInstance().reference.child("Users")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = arrayListOf<Users>()
                for (postSnapshot in dataSnapshot.children) {
                    val date = postSnapshot.child("date").getValue()
                    val email = postSnapshot.child("email").getValue()
                    val phone = postSnapshot.child("phone").getValue()
                    val password = postSnapshot.child("password").getValue()
                    val profileImage = postSnapshot.child("profileImage").getValue()
                    val isMe = postSnapshot.child("me").getValue()
                    val request = postSnapshot.child("request").getValue()
                    val username = postSnapshot.child("username").getValue()
                    val user = Users(username.toString(), email.toString(),password.toString(),date.toString()
                        ,phone.toString(), profileImage.toString() ,isMe.toString() ,null
                    )
                    list.add(user)
                    Log.i("KMFG", "onDataChange: "+ list.toString())
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