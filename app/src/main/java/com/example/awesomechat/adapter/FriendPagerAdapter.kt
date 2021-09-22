package com.example.awesomechat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.annotation.NonNull

import androidx.fragment.app.FragmentActivity
import com.example.awesomechat.fragment.*


class FriendPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MyFriendFragment()
            1 -> return AllUsersFragment()
            2 -> return RequestUsersFragment()
        }
        return HomeMessageFragment()
    }

    override fun getItemCount(): Int {
        return 3
    }
}