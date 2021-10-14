package com.example.awesomechat.ui.homemessage.friend.myfriend

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.model.Request
import com.example.awesomechat.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MyFriendViewModel @Inject constructor(
    @ApplicationContext private val context: Context
): BaseViewModel(){
    private val auth by lazy { FirebaseAuth.getInstance() }
    var listMyFriend: MutableLiveData<ArrayList<Users>> = MutableLiveData()
    private lateinit var dataRef: DatabaseReference
    fun getMyFriend() {
        dataRef = FirebaseDatabase.getInstance().reference.child("Users")
            .child(auth.currentUser!!.displayName.toString())
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listFriend = arrayListOf<Users>()
                for (postSnapshot in dataSnapshot.children) {
                    val profileImage = postSnapshot.child("profileImage").value
                    val isFriend = postSnapshot.child("isFriend").value
                    val username = postSnapshot.child("name").value
                    val myFriend = Users(username.toString(),  profileImage.toString(),isFriend.toString())
                    if(isFriend!!.equals("friend")){
                        listFriend.add(myFriend)
                    }
                }
                listMyFriend.value = listFriend

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }

}
