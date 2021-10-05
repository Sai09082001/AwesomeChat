package com.example.awesomechat.fragment

import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.databinding.RegisterFragmentBinding
import com.example.awesomechat.model.Users
import com.example.awesomechat.viewmodel.RegisterViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<RegisterFragmentBinding, RegisterViewModel>(){
    private lateinit var mAuth : FirebaseAuth
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
//            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
//                OnCompleteListener {
//                    if (it.isSuccessful){
//                        NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
//                    }
//                })
             val user = Users(userName,email,password,"","","","",null)
             FirebaseDatabase.getInstance().getReference().child("Users").push().setValue(user)

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