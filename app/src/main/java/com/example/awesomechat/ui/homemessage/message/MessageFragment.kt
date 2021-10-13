package com.example.awesomechat.ui.homemessage.message

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.MessageFragmentBinding
import com.example.awesomechat.model.GroupMessage
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.utils.BundleKey
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MessageFragment : BaseFragment<MessageFragmentBinding>(), MessageAdapter.OnItemClickListener {
    private lateinit var messageAdapter: MessageAdapter

    @Inject
    lateinit var appNavigation: AppNavigation

    override fun initViews() {
        binding.rvMessage.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvMessage.addItemDecoration(dividerItemDecoration)
        subcriData()
    }

    private fun subcriData() {
        viewModel.loadGroupChat()
        viewModel.listGroupChat.observe(viewLifecycleOwner, Observer {
            it.let {
                messageAdapter = MessageAdapter(requireContext(), it)
                messageAdapter.setOnItemListener(this)
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
    override fun onItemClick(groupMessage: GroupMessage) {
        val bundle = Bundle()
        bundle.putString(BundleKey.KEY_ID_GROUP, groupMessage.idGroup)
        bundle.putString(BundleKey.KEY_FRIEND, groupMessage.friendName)
        bundle.putString(BundleKey.KEY_IMAGE_GROUP, groupMessage.imageGroup)
        appNavigation.openGroupToMessageDetailScreen(bundle)
    }
}