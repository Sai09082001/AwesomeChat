package com.example.awesomechat.ui.requestuser

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.RequestUsersFragmentBinding
import com.example.awesomechat.ui.homemessage.HomeMessageViewModel

class RequestUsersFragment : BaseFragment<RequestUsersFragmentBinding>() {
    private lateinit var requestAdapter: RequestAdapter
    override fun initViews() {
        Toast.makeText(context, "Request Users Fragment", Toast.LENGTH_SHORT).show()
        binding.rvRequestFriend.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvRequestFriend.addItemDecoration(dividerItemDecoration)
        subcriData()
    }

    private fun subcriData() {
        viewModel.getUserRequest()
//        mViewModel!!.listRequest.observe(viewLifecycleOwner, Observer {
//            it.let {
//                requestAdapter = RequestAdapter(requireContext(), it)
//                binding!!.rvRequestFriend.adapter = requestAdapter
//            }
//        })
    }

    override fun initBinding(mRootView: View): RequestUsersFragmentBinding {
        return RequestUsersFragmentBinding.bind(mRootView)
    }

    val viewModel: RequestUsersViewModel by viewModels()

    override fun getLayoutId(): Int {
        return R.layout.request_users_fragment
    }

    override fun getVM(): RequestUsersViewModel = viewModel
}