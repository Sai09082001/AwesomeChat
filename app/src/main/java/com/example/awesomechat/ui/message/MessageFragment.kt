package com.example.awesomechat.ui.message

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.MessageFragmentBinding

class MessageFragment : BaseFragment<MessageFragmentBinding>() {
    private lateinit var messageAdapter: MessageAdapter

    override fun initViews() {
        Toast.makeText(context, "Message Fragment", Toast.LENGTH_SHORT).show()
        binding.rvMessage.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvMessage.addItemDecoration(dividerItemDecoration)
        subcriData()
    }

    private fun subcriData() {
        viewModel.loadAllUsers()
        viewModel.listUsers.observe(viewLifecycleOwner, Observer {
            // listUsers.addAll(mViewModel!!.listUsers.value!!)
            it.let {
                messageAdapter = MessageAdapter(requireContext(), it)
                binding.rvMessage.adapter = messageAdapter
            }
        })
    }

    override fun initBinding(mRootView: View): MessageFragmentBinding {
        return MessageFragmentBinding.bind(mRootView)
    }

    override fun getLayoutId(): Int {
        return R.layout.message_fragment
    }

    override fun getVM(): MessageViewModel = viewModel
    val viewModel: MessageViewModel by viewModels()
}