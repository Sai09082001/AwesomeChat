package com.example.awesomechat.fragment

import android.view.View
import com.example.awesomechat.R
import com.example.awesomechat.adapter.FriendPagerAdapter
import com.example.awesomechat.databinding.FriendFragmentBinding
import com.example.awesomechat.viewmodel.FriendViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FriendFragment : BaseFragment<FriendFragmentBinding, FriendViewModel>() {
    private lateinit var myPagerAdapter: FriendPagerAdapter
    override fun initViews() {
        myPagerAdapter = FriendPagerAdapter(requireActivity())
        binding!!.vpFriend.adapter = myPagerAdapter
        TabLayoutMediator(binding!!.tlFriend,binding!!.vpFriend, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                when(position){
                    0 -> tab.text = "Bạn bè"
                    1 -> tab.text = "Tất cả"
                    2 -> tab.text = "Yêu cầu"
                }
            }

        }).attach()
    }

    override fun initBinding(mRootView: View): FriendFragmentBinding? {
        return FriendFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<FriendViewModel> {
        return FriendViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.friend_fragment
    }
}