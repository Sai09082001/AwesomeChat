package com.example.awesomechat.ui.editprofile

import android.content.Context
import com.example.awesomechat.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    @ApplicationContext context: Context
) : BaseViewModel() {
//    var listUsers : MutableLiveData<ArrayList<Users>> = MutableLiveData()
//    private lateinit var dataRef : DatabaseReference
//    fun loadAllUsers() {
//        dataRef = FirebaseDatabase.getInstance().reference.child("Users")
//        dataRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val list = arrayListOf<Users>()
//                for (postSnapshot in dataSnapshot.children) {
//                    val date = postSnapshot.child("date").getValue()
//                    val email = postSnapshot.child("email").getValue()
//                    val phone = postSnapshot.child("phone").getValue()
//                    val password = postSnapshot.child("password").getValue()
//                    val profileImage = postSnapshot.child("profileImage").getValue()
//                    val request = postSnapshot.child("request").getValue()
//                    val username = postSnapshot.child("username").getValue()
//                    val user = Users(username.toString(), email.toString(),password.toString(),date.toString(),phone.toString(),
//                        profileImage.toString() , request.toString()
//                    )
//                    list.add(user)
//                    Log.i("KMFG", "onDataChange: "+ list.toString())
//                }
//                listUsers.value = list
//
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
//                // ...
//            }
//        })
//    }
}
