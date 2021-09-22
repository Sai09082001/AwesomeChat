package com.example.awesomechat.fragment

import android.view.View
import android.widget.Toast
import com.example.awesomechat.R
import com.example.awesomechat.databinding.RequestUsersFragmentBinding
import com.example.awesomechat.viewmodel.RequestUsersViewModel

class RequestUsersFragment : BaseFragment<RequestUsersFragmentBinding, RequestUsersViewModel>() {
    override fun initViews() {
        Toast.makeText(context,"Request Users Fragment", Toast.LENGTH_SHORT).show()
    }

    override fun initBinding(mRootView: View): RequestUsersFragmentBinding? {
        return RequestUsersFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<RequestUsersViewModel> {
        return RequestUsersViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.request_users_fragment
    }
}