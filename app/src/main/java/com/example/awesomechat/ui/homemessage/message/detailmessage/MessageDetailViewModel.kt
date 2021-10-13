package com.example.awesomechat.ui.homemessage.message.detailmessage

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.model.GroupMessage
import com.example.awesomechat.model.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MessageDetailViewModel @Inject constructor(
    @ApplicationContext private val context: Context
)  : BaseViewModel(){
    val listMessageDetail = MutableLiveData<ArrayList<Message>>()
    private val auth by lazy { FirebaseAuth.getInstance() }
    fun loadMessageDetail(key : String) {
        val dataRef =FirebaseDatabase.getInstance().reference.child("Message")
            .child(auth.currentUser!!.displayName.toString()).child(key)
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = arrayListOf<Message>()
                for (postSnapshot in dataSnapshot.children) {
                    val sender = postSnapshot.child("sender").value
                    val time = postSnapshot.child("time").value
                    val content = postSnapshot.child("content").value
                    val message = Message(
                        sender.toString(),
                        time.toString(),
                        content.toString()
                    )
                    list.add(message)
                }
                listMessageDetail.value = list

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }
}