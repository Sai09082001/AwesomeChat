package com.example.awesomechat.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.awesomechat.model.Users
import com.google.firebase.database.*

abstract class BaseViewModel : ViewModel() {
    var listUsers: MutableLiveData<ArrayList<Users>> = MutableLiveData()
    private lateinit var dataRef: DatabaseReference
    fun loadAllUsers() {
        dataRef = FirebaseDatabase.getInstance().reference.child("Users")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = arrayListOf<Users>()
                for (postSnapshot in dataSnapshot.children) {
                    val date = postSnapshot.child("date").value
                    val email = postSnapshot.child("email").value
                    val phone = postSnapshot.child("phone").value
                    val password = postSnapshot.child("password").value
                    val profileImage = postSnapshot.child("profileImage").value
                    val isMe = postSnapshot.child("me").value
                    val request = postSnapshot.child("request").value
                    val username = postSnapshot.child("username").value
                    val user = Users(
                        username.toString(),
                        email.toString(),
                        password.toString(),
                        date.toString(),
                        phone.toString(),
                        profileImage.toString(),
                        isMe.toString()
                    )
                    list.add(user)
                    Log.i("KMFG", "onDataChange: " + list.toString())
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

    fun loadFriends() {
        dataRef = FirebaseDatabase.getInstance().reference.child("Friends")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = arrayListOf<Users>()
                for (postSnapshot in dataSnapshot.children) {
                    val date = postSnapshot.child("date").value
                    val email = postSnapshot.child("email").value
                    val phone = postSnapshot.child("phone").value
                    val password = postSnapshot.child("password").value
                    val profileImage = postSnapshot.child("profileImage").value
                    val isMe = postSnapshot.child("me").value
                    val request = postSnapshot.child("request").value
                    val username = postSnapshot.child("username").value
                    val user = Users(
                        username.toString(),
                        email.toString(),
                        password.toString(),
                        date.toString(),
                        phone.toString(),
                        profileImage.toString(),
                        isMe.toString()
                    )
                    list.add(user)
                    Log.i("KMFG", "onDataChange: " + list.toString())
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