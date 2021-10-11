package com.example.awesomechat.ui.register

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.RegisterFragmentBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding, RegisterViewModel>() {
    private lateinit var mAuth: FirebaseAuth
    override fun initViews() {
        mAuth = FirebaseAuth.getInstance()
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
            Toast.makeText(context, "oki", Toast.LENGTH_SHORT).show()
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                OnCompleteListener {
                    if (it.isSuccessful) {
                        NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
                    }
                })

        })
        binding!!.tvLoginNow.setOnClickListener(View.OnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
        })
    }

    override fun initBinding(mRootView: View): RegisterFragmentBinding {
        return RegisterFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<RegisterViewModel> {
        return RegisterViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.register_fragment
    }

}