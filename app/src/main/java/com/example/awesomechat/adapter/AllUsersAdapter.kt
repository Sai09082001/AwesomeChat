package com.example.awesomechat.adapter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomechat.KeyFileShare
import com.example.awesomechat.R
import com.example.awesomechat.databinding.ItemUserBinding
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.math.log


class AllUsersAdapter(val context : Context, var listUsers :ArrayList<Users>) :
    RecyclerView.Adapter<AllUsersAdapter.AllUsersHolder>(){

    class AllUsersHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemUserBinding = ItemUserBinding.bind(view)
        init {
            binding.tvAddFriend.setOnClickListener(View.OnClickListener {
                // do something
                val hashMap : HashMap<String, Any>
                        = HashMap<String, Any> ()

                // put() function
                hashMap.put("request" , binding.tvEmail.text.toString())
                val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
                dataRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (postSnapshot in dataSnapshot.children) {
                            if(postSnapshot.child("me").value!!.equals("me")){
                                dataRef.child(postSnapshot.key.toString()).child("request").child(binding.tvUserName.text.toString())
                                    .updateChildren(hashMap)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })
//                dataRef.addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        for (postSnapshot in dataSnapshot.children) {
//                            if(postSnapshot.child("me").value!!.equals("me") &&
//                                postSnapshot.child("username").value!!.equals(binding.tvUserName.text.toString())){
//                                dataRef.child(postSnapshot.key.toString()).child("request").
//                                setValue(postSnapshot.child("email").value.toString())
//                            }
//                        }
//                    }
//
//                    override fun onCancelled(databaseError: DatabaseError) {
//                        // Getting Post failed, log a message
//                        Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
//                        // ...
//                    }
//                })
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUsersHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return AllUsersHolder(view)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: AllUsersHolder, position: Int) {
//        if(listUsers[position].listRequest.toString().equals("")){
//            holder.binding.tvAddFriend.visibility = View.VISIBLE
//        }else if(!listUsers[position].listRequest.toString().equals("")){
//            holder.binding.tvAddFriend.visibility = View.GONE
//        }
        holder.binding.tvEmail.text = listUsers[position].mail.toString()
        holder.binding.tvUserName.text = listUsers[position].userName.toString()
        Glide.with(context).load(listUsers[position].profileImage.toString()).into(holder.binding.ivProfile)
    }
}