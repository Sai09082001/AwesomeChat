package com.example.awesomechat.ui.homemessage.message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.databinding.ItemMessageBinding
import com.example.awesomechat.model.GroupMessage

class MessageAdapter(val context : Context, var listGroupMessage :ArrayList<GroupMessage>) :
    RecyclerView.Adapter<MessageAdapter.MessageHolder>(){

    interface OnItemClickListener {
        fun onItemClick(groupMessage : GroupMessage)
    }
    private lateinit var mListener: OnItemClickListener

    fun setOnItemListener(mListener : OnItemClickListener){
        this.mListener = mListener
    }
    class MessageHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemMessageBinding = ItemMessageBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false)
        return MessageHolder(view)
    }

    override fun getItemCount(): Int {
        return listGroupMessage.size
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
//        if(listUsers[position].listRequest.toString().equals("")){
//            holder.binding.tvAddFriend.visibility = View.VISIBLE
//        }else if(!listUsers[position].listRequest.toString().equals("")){
//            holder.binding.tvAddFriend.visibility = View.GONE
//        }
        holder.binding.frameGroup.setOnClickListener(View.OnClickListener {
            mListener.onItemClick(listGroupMessage[position])
        })
        holder.binding.tvLastMessage.text = listGroupMessage[position].lastMessage
        holder.binding.tvLastTime.text = listGroupMessage[position].lastTime
        holder.binding.tvUserName.text = listGroupMessage[position].friendName
        Glide.with(context).load(listGroupMessage[position].imageGroup).into(holder.binding.ivProfile)
    }
}