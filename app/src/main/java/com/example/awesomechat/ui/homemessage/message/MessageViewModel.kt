package com.example.awesomechat.ui.homemessage.message

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.base.BaseViewModel
import com.example.awesomechat.model.GroupMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    @ApplicationContext private val context: Context
)  : BaseViewModel(){
    val listGroupChat = MutableLiveData<ArrayList<GroupMessage>>()
    private val auth by lazy { FirebaseAuth.getInstance() }
    private val dataRef by lazy { FirebaseDatabase.getInstance().reference.child("Group")
        .child(auth.currentUser!!.displayName.toString()) }
    fun loadGroupChat() {
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = arrayListOf<GroupMessage>()
                for (postSnapshot in dataSnapshot.children) {
                    val idGroup = postSnapshot.key.toString()
                    val imageGroup = postSnapshot.child("imageGroup").value
                    val friendName = postSnapshot.child("friendName").value
                    val lastMessage = postSnapshot.child("lastMessage").value
                    val lastTime = postSnapshot.child("lastTime").value
                    val unSeen = postSnapshot.child("unSeen").value
                    val groupMessage = GroupMessage(
                        idGroup,
                        friendName.toString(),
                        imageGroup.toString(),
                        lastMessage.toString(),
                        lastTime.toString(),
                        unSeen.toString()
                    )
                    list.add(groupMessage)
                    Log.i("KMFG", "onDataChange: " + list.toString())
                }
                listGroupChat.value = list

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }
}
