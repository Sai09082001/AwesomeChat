package com.example.awesomechat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.awesomechat.fragment.*

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