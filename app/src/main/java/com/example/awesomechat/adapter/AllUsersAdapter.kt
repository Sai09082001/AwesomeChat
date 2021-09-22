package com.example.awesomechat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.model.Users
import de.hdodenhof.circleimageview.CircleImageView


class AllUsersAdapter( val context : Context,val listUsers :ArrayList<Users>) :
    RecyclerView.Adapter<AllUsersAdapter.Companion.AllUsersHolder>() {

    companion object{
        class AllUsersHolder(val mContext: Context,view :View) :RecyclerView.ViewHolder(view){
            val ivProfile = view.findViewById<CircleImageView>(R.id.iv_profile)
            val tvUserName = view.findViewById<AppCompatTextView>(R.id.tv_user_name)
            init {
                view.setOnClickListener{
                    it.startAnimation(AnimationUtils.loadAnimation(mContext,androidx.appcompat.R.anim.abc_popup_enter))
                    // do something
                }
            }

        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUsersHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false)
        return AllUsersHolder(context,view)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: AllUsersHolder, position: Int) {
        holder.tvUserName.text = listUsers[position].userName.toString()
        Glide.with(context).load(listUsers[position].profileImage.toString()).into(holder.ivProfile)
    }
}