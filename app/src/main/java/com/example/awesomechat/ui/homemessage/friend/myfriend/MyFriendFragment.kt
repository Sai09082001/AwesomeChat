package com.example.awesomechat.ui.homemessage.friend.myfriend

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.MyFriendFragmentBinding
import com.example.awesomechat.ui.homemessage.friend.requestuser.RequestAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyFriendFragment : BaseFragment<MyFriendFragmentBinding>() {
    private lateinit var myFriendAdapter: MyFriendAdapter
    override fun initViews() {
        binding.rcvMyFriend.layoutManager = LinearLayoutManager(context)
        myFriendAdapter = MyFriendAdapter(requireContext())
        subcriData()
    }

    private fun subcriData() {
        viewModel.getMyFriend()
        viewModel.listMyFriend.observe(viewLifecycleOwner){
            it.let {
                myFriendAdapter.setListFriend(it)
                binding.rcvMyFriend.adapter = myFriendAdapter
            }
        }
    }

    override fun initBinding(mRootView: View): MyFriendFragmentBinding {
        return MyFriendFragmentBinding.bind(mRootView)
    }

    val viewModel: MyFriendViewModel by viewModels()

    override fun getLayoutId(): Int = R.layout.my_friend_fragment

    override fun getVM(): MyFriendViewModel= viewModel
}