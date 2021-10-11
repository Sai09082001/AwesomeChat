package com.example.awesomechat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.awesomechat.fragment.AllUsersFragment
import com.example.awesomechat.fragment.HomeMessageFragment
import com.example.awesomechat.fragment.MyFriendFragment
import com.example.awesomechat.fragment.RequestUsersFragment


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