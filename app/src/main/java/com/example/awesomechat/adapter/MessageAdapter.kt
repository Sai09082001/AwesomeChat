package com.example.awesomechat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.databinding.ItemMessageBinding
import com.example.awesomechat.databinding.ItemUserBinding
import com.example.awesomechat.model.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MessageAdapter(val context : Context, var listUsers :ArrayList<Users>) :
    RecyclerView.Adapter<MessageAdapter.MessageHolder>(){

    class MessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemMessageBinding = ItemMessageBinding.bind(view)
        init {
//            binding.tvAddFriend.setOnClickListener(View.OnClickListener {
//                // do something
//                val hashMap : HashMap<String, Any>
//                        = HashMap<String, Any> ()
//
//                // put() function
//                hashMap.put("request" , binding.tvEmail.text.toString())
//                val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
//                dataRef.addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        for (postSnapshot in dataSnapshot.children) {
//                            if(postSnapshot.child("me").value!!.equals("me")){
//                                dataRef.child(postSnapshot.key.toString()).child("request").child(binding.tvUserName.text.toString())
//                                    .updateChildren(hashMap)
//                            }
//                        }
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        TODO("Not yet implemented")
//                    }
//                })
//            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false)
        return MessageHolder(view)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
//        if(listUsers[position].listRequest.toString().equals("")){
//            holder.binding.tvAddFriend.visibility = View.VISIBLE
//        }else if(!listUsers[position].listRequest.toString().equals("")){
//            holder.binding.tvAddFriend.visibility = View.GONE
//        }
        holder.binding.tvUserName.text = listUsers[position].userName.toString()
        Glide.with(context).load(listUsers[position].profileImage.toString()).into(holder.binding.ivProfile)
       // Glide.with(context).load(listUsers[position].profileImage.toString()).into(holder.binding.ivProfile)
    }
}