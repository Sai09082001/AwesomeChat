package com.example.awesomechat.ui.register

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.RegisterFragmentBinding
import com.example.awesomechat.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding>() {

    @Inject
    lateinit var appNavigation: AppNavigation

    override fun initViews() {
        binding.tvRegister.setOnClickListener(View.OnClickListener {
            viewModel.doUserRegister(binding.edtEmailRegis.text.toString(), binding.edtPasswordRegis.text.toString())
        })
        viewModel.stateRegister.observe(viewLifecycleOwner){
            if(it){
                viewModel.setUserName(binding.edtNameRegis.text.toString())
                appNavigation.openRegisterToLoginScreen()
            }else Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show()
        }
        binding.tvLoginNow.setOnClickListener(View.OnClickListener {
            appNavigation.openRegisterToLoginScreen()
        })
        viewModel.pass_Email_Valid.observe(viewLifecycleOwner){
            if(it){
                binding.tvRegister.isEnabled = true
                binding.tvRegister.setBackgroundResource(R.drawable.custom_friend_request)
            }else {
                binding.tvRegister.isEnabled = false
                binding.tvRegister.setBackgroundResource(R.drawable.custom_button_login)
            }
        }
    }

    override fun initBinding(mRootView: View): RegisterFragmentBinding {
        return RegisterFragmentBinding.bind(mRootView)
    }

    override fun getLayoutId(): Int {
        return R.layout.register_fragment
    }

    override fun getVM(): RegisterViewModel = viewModel
    val viewModel: RegisterViewModel by viewModels()
}