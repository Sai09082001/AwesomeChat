package com.example.awesomechat.ui.homemessage.friend.myfriend

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface StickyRvHeaderAdapter <VH : RecyclerView.ViewHolder?> {

        fun getHeaderId(position: Int): Char

        fun onCreateHeaderViewHolder(parent: ViewGroup?): VH

        fun onBindHeaderViewHolder(holder: VH, position: Int)

        fun getItemCount() : Int
}