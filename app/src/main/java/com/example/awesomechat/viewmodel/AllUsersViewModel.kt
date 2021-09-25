package com.example.awesomechat.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.awesomechat.model.Users
import com.google.firebase.database.*

class AllUsersViewModel : BaseViewModel(){
     var listUsers : MutableLiveData<ArrayList<Users>> = MutableLiveData()
     private lateinit var dataRef : DatabaseReference
     fun loadAllUsers() {
          dataRef = FirebaseDatabase.getInstance().reference.child("Users")
          // listUsers.removeAll(listUsers)
          dataRef.addValueEventListener(object : ValueEventListener {
               override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val list = arrayListOf<Users>()
                    for (postSnapshot in dataSnapshot.children) {
                         val date = postSnapshot.child("date").getValue()
                         val name = postSnapshot.child("name").getValue()
                         val phone = postSnapshot.child("phone").getValue()
                         val profileImage = postSnapshot.child("profileImage").getValue()
                         val status = postSnapshot.child("status").getValue()
                         val user = Users(name.toString(), date.toString(),phone.toString(),profileImage.toString() ,
                              status as Boolean?
                         )
                         list.add(user)
                         Log.i("KMFG", "onDataChange: "+ user.status)
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

//     private fun addAllUsers(users: Users) {
//               val list = listUsers.value
//               list?.add(users)
//               listUsers.value = list
//     }

     fun updateUsers(txt : String){
          val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
          var hashMap : HashMap<String, Any>
                  = HashMap<String, Any> ()

          hashMap.put("status", true)
          dataRef.addValueEventListener(object : ValueEventListener {
               override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (postSnapshot in dataSnapshot.children) {
                         if(postSnapshot.child("request").getValue()!! == false &&
                              postSnapshot.child("name").getValue()!!.equals(txt)){
                              dataRef.child(postSnapshot.key.toString()).child("request").setValue(true)
                         }else if(postSnapshot.child("request").getValue()!! == true &&
                              postSnapshot.child("name").getValue()!!.equals(txt)) {
                              dataRef.child(postSnapshot.key.toString()).child("request").setValue(false)
                         }
                    }
               }

               override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                    // ...
               }
          })
     }

}
