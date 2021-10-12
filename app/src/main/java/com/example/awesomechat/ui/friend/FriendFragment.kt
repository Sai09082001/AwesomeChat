package com.example.awesomechat.ui.friend

import android.view.View
import androidx.fragment.app.viewModels
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.FriendFragmentBinding
import com.example.awesomechat.ui.login.LoginViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FriendFragment : BaseFragment<FriendFragmentBinding>() {
    private lateinit var myPagerAdapter: FriendPagerAdapter
    override fun initViews() {
        myPagerAdapter = FriendPagerAdapter(requireActivity())
        binding.vpFriend.adapter = myPagerAdapter
        TabLayoutMediator(
            binding.tlFriend,
            binding.vpFriend,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    when (position) {
                        0 -> tab.text = "Bạn bè"
                        1 -> tab.text = "Tất cả"
                        2 -> tab.text = "Yêu cầu"
                    }
                }

            }).attach()
        binding.searchFriend.tvTitleSearch.hint = "Tìm kiếm bạn bè"

    }

    override fun initBinding(mRootView: View): FriendFragmentBinding {
        return FriendFragmentBinding.bind(mRootView)
    }

    override fun getLayoutId(): Int {
        return R.layout.friend_fragment
    }

    override fun getVM(): FriendViewModel = viewModel

    val viewModel: FriendViewModel by viewModels()

}