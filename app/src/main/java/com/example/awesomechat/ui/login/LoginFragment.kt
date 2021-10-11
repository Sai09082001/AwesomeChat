package com.example.awesomechat.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.utils.KeyFileShare.FILE_NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {
    private lateinit var edtEmail: AppCompatEditText
    private lateinit var edtPassword: AppCompatEditText

    @Inject
    lateinit var appNavigation: AppNavigation

    override fun initViews() {
        edtEmail = findViewById<AppCompatEditText>(R.id.edt_your_email)!!
        edtPassword = findViewById<AppCompatEditText>(R.id.edt_your_password)!!
        checkUserLogin()
        binding.tvLogin.setOnClickListener(View.OnClickListener {
            if (edtEmail.text.toString().isEmpty()) {
                Toast.makeText(context, "email is required", Toast.LENGTH_SHORT).show()
            }

            if (edtPassword.text.toString().isEmpty()) {
                Toast.makeText(context, "password is required", Toast.LENGTH_SHORT).show()
            }
            if (!edtEmail.text.toString().isEmpty() && !edtPassword.text.toString().isEmpty()) {
                mViewModel.doUserLogin(edtEmail.text.toString(), edtPassword.text.toString())
                mViewModel.stateLogin.observe(this, Observer {
                    if (it) {
                        appNavigation.openLoginToHomeScreen()
                    } else {
                        Toast.makeText(context, "Email or password not true", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }

        })

        binding.tvRegisterNow.setOnClickListener({
            appNavigation.openLoginToRegisterScreen()
        })
    }

    private fun checkUserLogin() {
        mViewModel.setValue()
        mViewModel.handleLogin()
        mViewModel.navigateLogin.observe(this, Observer {
            if (it) {
                appNavigation.openLoginToHomeScreen()
            }
        })
    }

    fun savePref(key: String?, value: String?) {
        val pref: SharedPreferences =
            requireContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        pref.edit().putString(key, value).apply()
    }

    override fun initBinding(mRootView: View): LoginFragmentBinding {
        return LoginFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.login_fragment
    }

}