package com.example.awesomechat.ui.homemessage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.awesomechat.ui.friend.FriendFragment
import com.example.awesomechat.ui.homemessage.HomeMessageFragment
import com.example.awesomechat.ui.message.MessageFragment
import com.example.awesomechat.ui.mypage.MyPageFragment

class HomePagerAdapter (fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return MessageFragment()
                1 -> return FriendFragment()
                2 -> return MyPageFragment()
            }
            return HomeMessageFragment()
        }

        override fun getItemCount(): Int {
            return 3
        }
}