package com.example.awesomechat.fragment

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awesomechat.R
import com.example.awesomechat.adapter.RequestAdapter
import com.example.awesomechat.databinding.RequestUsersFragmentBinding
import com.example.awesomechat.viewmodel.RequestUsersViewModel

class RequestUsersFragment : BaseFragment<RequestUsersFragmentBinding, RequestUsersViewModel>() {
    private lateinit var requestAdapter: RequestAdapter
    override fun initViews() {
        Toast.makeText(context, "Request Users Fragment", Toast.LENGTH_SHORT).show()
        binding!!.rvRequestFriend.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding!!.rvRequestFriend.addItemDecoration(dividerItemDecoration)
        subcriData()
    }

    private fun subcriData() {
        mViewModel!!.getUserRequest()
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

    override fun getViewModelClass(): Class<RequestUsersViewModel> {
        return RequestUsersViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.request_users_fragment
    }
}