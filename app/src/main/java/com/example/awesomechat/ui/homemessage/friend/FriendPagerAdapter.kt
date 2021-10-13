package com.example.awesomechat.ui.homemessage.friend

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.awesomechat.ui.homemessage.friend.alluser.AllUsersFragment
import com.example.awesomechat.ui.homemessage.HomeMessageFragment
import com.example.awesomechat.ui.homemessage.friend.myfriend.MyFriendFragment
import com.example.awesomechat.ui.homemessage.friend.requestuser.RequestUsersFragment


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