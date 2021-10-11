package com.example.awesomechat.message

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awesomechat.R
import com.example.awesomechat.adapter.MessageAdapter
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.MessageFragmentBinding

class MessageFragment : BaseFragment<MessageFragmentBinding, MessageViewModel>() {
    private lateinit var messageAdapter: MessageAdapter

    override fun initViews() {
        Toast.makeText(context, "Message Fragment", Toast.LENGTH_SHORT).show()
        binding!!.rvMessage.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding!!.rvMessage.addItemDecoration(dividerItemDecoration)
        subcriData()
    }

    private fun subcriData() {
        mViewModel!!.loadAllUsers()
        mViewModel!!.listUsers.observe(viewLifecycleOwner, Observer {
            // listUsers.addAll(mViewModel!!.listUsers.value!!)
            it.let {
                messageAdapter = MessageAdapter(requireContext(), it)
                binding!!.rvMessage.adapter = messageAdapter
            }
        })
    }

    override fun initBinding(mRootView: View): MessageFragmentBinding {
        return MessageFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<MessageViewModel> {
        return MessageViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.message_fragment
    }
}