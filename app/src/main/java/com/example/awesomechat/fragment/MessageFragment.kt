package com.example.awesomechat.fragment

import android.view.View
import android.widget.Toast
import com.example.awesomechat.R
import com.example.awesomechat.databinding.MessageFragmentBinding
import com.example.awesomechat.viewmodel.MessageViewModel

class MessageFragment : BaseFragment<MessageFragmentBinding, MessageViewModel>() {
    override fun initViews() {
        Toast.makeText(context,"Message Fragment",Toast.LENGTH_SHORT).show()
    }

    override fun initBinding(mRootView: View): MessageFragmentBinding? {
        return MessageFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<MessageViewModel> {
         return MessageViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.message_fragment
    }
}