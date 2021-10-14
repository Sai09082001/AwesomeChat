package com.example.awesomechat.ui.homemessage.friend.myfriend

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.databinding.ItemHeaderBinding
import com.example.awesomechat.databinding.ItemUserBinding
import com.example.awesomechat.model.Users

// lỗi phiêm bản rồi e
// import android.support.v7.widget.RecyclerView
// import androidx.recyclerview.widget.RecyclerView
class MyFriendAdapter(val context: Context) : RecyclerView.Adapter<MyFriendAdapter.FriendViewHolder>(),
    StickyRvHeaderAdapter<MyFriendAdapter.HeaderViewHolder> {

    private lateinit var listFriend: ArrayList<Users>

    @SuppressLint("NotifyDataSetChanged")
    fun setListFriend(listFriend: ArrayList<Users>) {
        this.listFriend = listFriend
        notifyDataSetChanged()
    }
    // thàng này có bị ngunguoif e
     class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemHeaderBinding.bind(itemView)

        init {

        }
    }

    class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemUserBinding.bind(itemView)

        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.binding.tvAddFriend.visibility = View.GONE
        holder.binding.tvUserName.text = listFriend[position].userName
        Glide.with(context).load(listFriend[position].profileImage).into(holder.binding.ivProfile)
    }

    override fun getHeaderId(position: Int): Char {
       return listFriend[position].userName.get(0)
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup?): HeaderViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_header, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.binding.tvHeader.text = listFriend[position].userName.substring(0,1)
    }

    override fun getItemCount(): Int {
        return listFriend.size
    }

}