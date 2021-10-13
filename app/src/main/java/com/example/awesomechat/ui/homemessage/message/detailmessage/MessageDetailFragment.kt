package com.example.awesomechat.ui.homemessage.message.detailmessage

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.MessageDetailFragmentBinding
import com.example.awesomechat.utils.BundleKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageDetailFragment : BaseFragment<MessageDetailFragmentBinding>() {
    val viewModel : MessageDetailViewModel by viewModels()
    override fun getVM(): ViewModel = viewModel
    override fun initViews() {
        val key = requireArguments().getString(BundleKey.KEY_ID_GROUP)
        binding.tvFriendName.text = requireArguments().getString(BundleKey.KEY_FRIEND)
        Glide.with(requireContext()).load(requireArguments().getString(BundleKey.KEY_IMAGE_GROUP)).into(binding.ivProfile)
        Toast.makeText(context,"Bundle"+key,Toast.LENGTH_SHORT).show()
    }

    override fun initBinding(mRootView: View): MessageDetailFragmentBinding {
        return MessageDetailFragmentBinding.bind(mRootView)
    }

    override fun getLayoutId(): Int = R.layout.message_detail_fragment
}