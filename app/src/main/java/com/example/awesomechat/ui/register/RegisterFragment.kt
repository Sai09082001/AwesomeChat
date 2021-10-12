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
            val userName = binding.edtNameRegis.text.toString()
            val email = binding.edtEmailRegis.text.toString()
            val password = binding.edtPasswordRegis.text.toString()
            viewModel.setUserName(userName)
            viewModel.doUserRegister(email, password)
//            if (TextUtils.isEmpty(userName)) {
//                Toast.makeText(context, "username is required", Toast.LENGTH_SHORT).show()
//            }
//            if (TextUtils.isEmpty(email)) {
//                Toast.makeText(context, "email is required", Toast.LENGTH_SHORT).show()
//            }
//
//            if (TextUtils.isEmpty(password)) {
//                Toast.makeText(context, "password is required", Toast.LENGTH_SHORT).show()
//            }
//            Toast.makeText(context, "oki", Toast.LENGTH_SHORT).show()
//            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
//                OnCompleteListener {
//                    if (it.isSuccessful) {
//                        NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
//                    }
//                })

        })
        viewModel.stateRegister.observe(viewLifecycleOwner){
            if(it){
                appNavigation.openRegisterToLoginScreen()
            }
        }
        binding.tvLoginNow.setOnClickListener(View.OnClickListener {
            appNavigation.openRegisterToLoginScreen()
        })
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