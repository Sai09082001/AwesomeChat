package com.example.awesomechat.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.databinding.ItemUserBinding
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.math.log


class AllUsersAdapter( val context : Context,val listUsers :ArrayList<Users>) :
    RecyclerView.Adapter<AllUsersAdapter.AllUsersHolder>() {

    class AllUsersHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemUserBinding = ItemUserBinding.bind(view)
        init {
            binding.tvAddFriend.setOnClickListener(View.OnClickListener {
                    // do something


            })
        }
    }


//    companion object{
//        class AllUsersHolder(val mContext: Context,view :View) :RecyclerView.ViewHolder(view){
//            val ivProfile = view.findViewById<CircleImageView>(R.id.iv_profile)
//            val tvUserName = view.findViewById<AppCompatTextView>(R.id.tv_user_name)
//            val tvAddFriend = view.findViewById<AppCompatTextView>(R.id.tv_add_friend)
//            init {
//                tvAddFriend.setOnClickListener{
//                    it.startAnimation(AnimationUtils.loadAnimation(mContext,androidx.appcompat.R.anim.abc_popup_enter))
//                    // do something
//                    val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
//                    var hashMap : HashMap<String, Any>
//                            = HashMap<String, Any> ()
//
//                    hashMap.put("status", true)
////                    val query= dataRef.child("Users").equalTo(tvUserName.text.toString())
////                    Log.i("KMFG", ": "+query)
////                    query.addValueEventListener(object : ValueEventListener{
////                        override fun onDataChange(snapshot: DataSnapshot) {
////
////                        }
////
////                        override fun onCancelled(error: DatabaseError) {
////                            TODO("Not yet implemented")
////                        }
////
////                    })
//                    dataRef.addValueEventListener(object : ValueEventListener {
//                        override fun onDataChange(dataSnapshot: DataSnapshot) {
//                            for (postSnapshot in dataSnapshot.children) {
//                                if( postSnapshot.child("name").getValue()!!.equals(tvUserName.text.toString())){
//                                    dataRef.child(postSnapshot.key.toString()).child("status").setValue(true)
//                                }
//                            }
//                        }
//
//                        override fun onCancelled(databaseError: DatabaseError) {
//                            // Getting Post failed, log a message
//                            Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
//                            // ...
//                        }
//                    })
//                }
//            }
//
//        }
//    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUsersHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return AllUsersHolder(view)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: AllUsersHolder, position: Int) {
        if(listUsers[position].request == false){
            holder.binding.tvAddFriend.visibility = View.VISIBLE
            holder.binding.tvUnfriend.visibility = View.GONE
            Log.i("KMFG", "onBindViewHolder: friend")
        }else if(listUsers[position].request == true) {
            holder.binding.tvUnfriend.visibility = View.VISIBLE
            holder.binding.tvAddFriend.visibility = View.GONE
            Log.i("KMFG", "onBindViewHolder: unfriend")
        }
        holder.binding.tvUserName.text = listUsers[position].userName.toString()
        Glide.with(context).load(listUsers[position].profileImage.toString()).into(holder.binding.ivProfile)
    }
}