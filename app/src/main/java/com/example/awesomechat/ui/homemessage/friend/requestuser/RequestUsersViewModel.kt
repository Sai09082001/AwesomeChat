package com.example.awesomechat.ui.homemessage.friend.requestuser

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.model.Request
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class RequestUsersViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): BaseViewModel() {
    private val auth by lazy { FirebaseAuth.getInstance() }
    var listFriendRequest: MutableLiveData<ArrayList<Request>> = MutableLiveData()
    var listYourRequest: MutableLiveData<ArrayList<Request>> = MutableLiveData()
    private lateinit var dataRef: DatabaseReference
    fun getFriendRequest() {
        dataRef = FirebaseDatabase.getInstance().reference.child("Request")
            .child(auth.currentUser!!.displayName.toString()).child("friendRequest")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listFriendReq = arrayListOf<Request>()
                for (postSnapshot in dataSnapshot.children) {
                    val profileImage = postSnapshot.child("profileImage").value
                    val stateRequest = postSnapshot.child("stateRequest").value
                    val username = postSnapshot.child("name").value
                    val friendReq = Request(username.toString(),stateRequest as Boolean,  profileImage.toString())
                    listFriendReq.add(friendReq)
                }
                listFriendRequest.value = listFriendReq

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }

    fun getYourRequest() {
        dataRef = FirebaseDatabase.getInstance().reference.child("Request")
            .child(auth.currentUser!!.displayName.toString()).child("yourRequest")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listYourReq = arrayListOf<Request>()
                for (postSnapshot in dataSnapshot.children) {
                    val profileImage = postSnapshot.child("profileImage").value
                    val stateRequest = postSnapshot.child("stateRequest").value
                    val username = postSnapshot.child("name").value
                    val friendReq = Request(username.toString(),stateRequest as Boolean,  profileImage.toString())
                    listYourReq.add(friendReq)
                }
                listYourRequest.value = listYourReq

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }
}
