package com.example.awesomechat.fragment

import android.view.View
import android.widget.Toast
import com.example.awesomechat.R
import com.example.awesomechat.databinding.MyFriendFragmentBinding
import com.example.awesomechat.viewmodel.MyFriendViewModel

class MyFriendFragment : BaseFragment<MyFriendFragmentBinding, MyFriendViewModel>() {
    override fun initViews() {
        Toast.makeText(context, "My Friend Fragment", Toast.LENGTH_SHORT).show()
    }

    override fun initBinding(mRootView: View): MyFriendFragmentBinding {
        return MyFriendFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<MyFriendViewModel> {
        return MyFriendViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.my_friend_fragment
    }
}