package com.example.awesomechat.ui.homemessage.friend.requestuser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.databinding.ItemRequestBinding
import com.example.awesomechat.model.Request

class RequestAdapter(val context: Context, val listRequest: ArrayList<Request>) :
    RecyclerView.Adapter<RequestAdapter.RequestHolder>() {

    class RequestHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ItemRequestBinding = ItemRequestBinding.bind(view)

        init {
                // do something
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
        holder.binding.tvAccept.visibility = View.VISIBLE
        holder.binding.tvUserName.text = listRequest[position].userName
        Glide.with(context).load(listRequest[position].profileImage)
            .into(holder.binding.ivProfile)
    }
}