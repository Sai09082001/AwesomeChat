package com.example.awesomechat.fragment

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.databinding.RegisterFragmentBinding
import com.example.awesomechat.model.Users
import com.example.awesomechat.viewmodel.RegisterViewModel
import com.google.firebase.database.FirebaseDatabase


class RegisterFragment : BaseFragment<RegisterFragmentBinding, RegisterViewModel>() {
    override fun initViews() {
        binding!!.tvRegister.setOnClickListener(View.OnClickListener {
            val userName = binding!!.edtNameRegis.text.toString()
            val email = binding!!.edtEmailRegis.text.toString()
            val password = binding!!.edtPasswordRegis.text.toString()

            if (TextUtils.isEmpty(userName)) {
                Toast.makeText(context, "username is required", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(context, "email is required", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(context, "password is required", Toast.LENGTH_SHORT).show()
            }
            val user = Users(userName,email,password)
            Toast.makeText(context, "oki", Toast.LENGTH_SHORT).show()
            FirebaseDatabase.getInstance().getReference().child("users").push().setValue(user)
            NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
        })
        binding!!.tvLoginNow.setOnClickListener(View.OnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
        })
    }

    override fun initBinding(mRootView: View): RegisterFragmentBinding? {
        return RegisterFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.register_fragment
    }
}