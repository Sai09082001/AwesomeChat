package com.example.awesomechat.fragment

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.KeyFileShare.FILE_NAME
import com.example.awesomechat.R
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.model.Users
import com.example.awesomechat.viewmodel.LoginViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {
    private lateinit var edtEmail: AppCompatEditText
    private lateinit var edtPassword: AppCompatEditText

    private var listUsers: ArrayList<Users>? = null
    private var auth: FirebaseAuth? = null
    override fun initViews() {
        listUsers = arrayListOf()
        auth = FirebaseAuth.getInstance()
        edtEmail = findViewById<AppCompatEditText>(R.id.edt_your_email)!!
        edtPassword = findViewById<AppCompatEditText>(R.id.edt_your_password)!!

        binding.tvLogin.setOnClickListener(View.OnClickListener {
            if (edtEmail.text.toString().isEmpty()) {
                Toast.makeText(context, "email is required", Toast.LENGTH_SHORT).show()
            }

            if (edtPassword.text.toString().isEmpty()) {
                Toast.makeText(context, "password is required", Toast.LENGTH_SHORT).show()
            }
            if(!edtEmail.text.toString().isEmpty() && !edtPassword.text.toString().isEmpty() ){
                doUserLogin(edtEmail.text.toString(), edtPassword.text.toString())
            }

        })

        binding.tvRegisterNow.setOnClickListener({
            NavHostFragment.findNavController(this).navigate(R.id.registerFragment)
        })
    }

    private fun doUserLogin(email: String, password: String) {
        auth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(
            OnCompleteListener {
                if (it.isSuccessful) {
                    NavHostFragment.findNavController(this).navigate(R.id.homeMessageFragment)
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