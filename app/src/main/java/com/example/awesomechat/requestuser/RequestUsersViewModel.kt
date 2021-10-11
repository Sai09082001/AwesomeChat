package com.example.awesomechat.requestuser

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.model.Request
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RequestUsersViewModel : BaseViewModel() {
    var listUserRequest: MutableLiveData<ArrayList<Request>> = MutableLiveData()
    var listMyRequest: MutableLiveData<ArrayList<Request>> = MutableLiveData()
    private lateinit var dataRef: DatabaseReference
    fun getUserRequest() {
        dataRef = FirebaseDatabase.getInstance().reference.child("Request")
            .child(FirebaseAuth.getInstance().currentUser!!.displayName.toString())
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listUser = arrayListOf<Request>()
                val listMy = arrayListOf<Request>()
                for (postSnapshot in dataSnapshot.children) {
                    val profileImage = postSnapshot.child("profileImage").value
                    val request = postSnapshot.child("request").value
                    val username = postSnapshot.child("name").value
                    val requestUser =
                        Request(username.toString(), request.toString(), profileImage.toString())
                    if (request!!.equals("user")) {
                        listUser.add(requestUser)
                    } else {
                        listMy.add(requestUser)
                    }

                }
                listUserRequest.value = listUser
                listMyRequest.value = listMy

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }
}
