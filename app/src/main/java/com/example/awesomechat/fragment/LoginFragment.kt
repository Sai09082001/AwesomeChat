package com.example.awesomechat.fragment

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.awesomechat.R
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.viewmodel.LoginViewModel

class LoginFragment: BaseFragment<LoginFragmentBinding, LoginViewModel>(){
    private var edtPhone: EditText? = null
    private var edtPass: EditText? = null
    private var edtConfirmPass: EditText? = null
    private var phone: String? = null
    private var pass: String? = null

    override fun initViews() {

    }

    override fun initBinding(mRootView: View): LoginFragmentBinding? {
        return LoginFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.login_fragment
    }

    override fun doClickView(v: View?) {

    }

}