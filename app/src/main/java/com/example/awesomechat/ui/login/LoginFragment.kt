package com.example.awesomechat.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.utils.KeyFileShare.FILE_NAME
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding>() {

    val viewModel: LoginViewModel by viewModels()

    override fun getVM(): LoginViewModel = viewModel

    @Inject
    lateinit var appNavigation: AppNavigation

    override fun initViews() {
        viewModel.navigateLogin.observe(viewLifecycleOwner) {
            if (it) {
                appNavigation.openLoginToHomeScreen()
            }
        }

        binding.tvLogin.setOnClickListener {
            // check chuyển sang viewModel
            viewModel.doUserLogin(binding.edtYourEmail.text.toString(), binding.edtYourPassword.text.toString())

        }

        viewModel.pass_Email_Valid.observe(viewLifecycleOwner){
            if(it){
                binding.tvLogin.isEnabled = true
                binding.tvLogin.setBackgroundResource(R.drawable.custom_friend_request)
            }else {
                binding.tvLogin.isEnabled = false
                binding.tvLogin.setBackgroundResource(R.drawable.custom_button_login)
            }
        }
        viewModel.stateLogin.observe(viewLifecycleOwner) {
            if (it) {
                appNavigation.openLoginToHomeScreen()
            } else {
                Toast.makeText(context, "Email or password not true", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        binding.tvRegisterNow.setOnClickListener {
            appNavigation.openLoginToRegisterScreen()
        }
    }

    fun savePref(key: String?, value: String?) {
        val pref: SharedPreferences =
            requireContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        pref.edit().putString(key, value).apply()
    }

    override fun initBinding(mRootView: View): LoginFragmentBinding {
        return LoginFragmentBinding.bind(mRootView)
    }

    override fun getLayoutId(): Int {
        return R.layout.login_fragment
    }

}