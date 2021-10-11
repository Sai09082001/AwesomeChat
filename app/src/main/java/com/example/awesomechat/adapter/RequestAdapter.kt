package com.example.awesomechat.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.databinding.ItemRequestBinding
import com.example.awesomechat.model.Request
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RequestAdapter(val context: Context, val listRequest: ArrayList<Request>) :
    RecyclerView.Adapter<RequestAdapter.RequestHolder>() {

    class RequestHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemRequestBinding = ItemRequestBinding.bind(view)

        init {
            binding.tvAccept.setOnClickListener(View.OnClickListener {
                // do something
                val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
                dataRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (postSnapshot in dataSnapshot.children) {
                            if (postSnapshot.child("status").value!!.equals("waiting") &&
                                postSnapshot.child("username").value!!
                                    .equals(binding.tvUserName.text.toString())
                            ) {
                                dataRef.child(postSnapshot.key.toString()).child("status")
                                    .setValue("friend")
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message
                        Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                        // ...
                    }
                })
            })
            binding.tvCancel.setOnClickListener(View.OnClickListener {
                // do something
                val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
                dataRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        for (postSnapshot in dataSnapshot.children) {
                            if (postSnapshot.child("status").value!!.equals("waiting") &&
                                postSnapshot.child("request").value!!.equals("true") &&
                                postSnapshot.child("username").value!!
                                    .equals(binding.tvUserName.text.toString())
                            ) {
                                dataRef.child(postSnapshot.key.toString()).child("status")
                                    .setValue("cancel")
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Getting Post failed, log a message
                        Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                        // ...
                    }
                })
            })
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_request, parent, false)
        return RequestHolder(view)
    }

    override fun getItemCount(): Int {
        return listRequest.size
    }

    override fun onBindViewHolder(holder: RequestHolder, position: Int) {
        if (listRequest[position].mailRequest.toString().equals("")) {
            holder.binding.tvAccept.visibility = View.VISIBLE
            holder.binding.tvCancel.visibility = View.GONE
        } else {
            holder.binding.tvAccept.visibility = View.GONE
            holder.binding.tvCancel.visibility = View.VISIBLE
        }
        holder.binding.tvUserName.text = listRequest[position].userName.toString()
        Glide.with(context).load(listRequest[position].profileImage.toString())
            .into(holder.binding.ivProfile)
        //  holder.binding.tvUserName.text = listUsers[position].userName.toString()
        // Glide.with(context).load(listUsers[position].profileImage.toString()).into(holder.binding.ivProfile)
    }
}