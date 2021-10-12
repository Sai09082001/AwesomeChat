package com.example.awesomechat.ui.myfriend

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.MyFriendFragmentBinding
import com.example.awesomechat.ui.homemessage.HomeMessageViewModel

class MyFriendFragment : BaseFragment<MyFriendFragmentBinding>() {
    override fun initViews() {
        Toast.makeText(context, "My Friend Fragment", Toast.LENGTH_SHORT).show()
    }

    override fun initBinding(mRootView: View): MyFriendFragmentBinding {
        return MyFriendFragmentBinding.bind(mRootView)
    }

    val viewModel: MyFriendViewModel by viewModels()

    override fun getLayoutId(): Int {
        return R.layout.my_friend_fragment
    }

    override fun getVM(): MyFriendViewModel= viewModel
}