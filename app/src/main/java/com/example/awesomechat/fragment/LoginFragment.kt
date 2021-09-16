package com.example.awesomechat.fragment

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.model.Users
import com.example.awesomechat.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*


class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {
    private lateinit var edtEmail: AppCompatEditText
    private lateinit var edtPassword: AppCompatEditText
    private var databaseRef: DatabaseReference? = null
    private var listUsers: ArrayList<Users>? = null
    private var auth: FirebaseAuth? = null
    override fun initViews() {
        listUsers = ArrayList()
        auth = FirebaseAuth.getInstance()
        edtEmail = findViewById<AppCompatEditText>(R.id.edt_your_email)!!
        edtPassword = findViewById<AppCompatEditText>(R.id.edt_your_password)!!
        databaseRef = FirebaseDatabase.getInstance().reference.child("users")
        binding!!.tvLogin.setOnClickListener(View.OnClickListener {
            checkDataUsers()
            for (i in listUsers!!.indices) {
                if (listUsers!![i].mail.equals(edtEmail.text.toString())
                    &&  listUsers!![i].password.equals(edtPassword.text.toString())) {
                    NavHostFragment.findNavController(this).navigate(R.id.homeMessageFragment)
                }
            }
        })
        binding!!.tvRegisterNow.setOnClickListener({
            NavHostFragment.findNavController(this).navigate(R.id.registerFragment)
        })
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

    private fun checkDataUsers() {
        databaseRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listUsers!!.clear()
                val keys: MutableList<String?> = ArrayList()
                for (keyNote in dataSnapshot.children) {
                    keys.add(keyNote.key)
                    val user = keyNote.getValue(Users::class.java)
                    listUsers!!.add(user!!)
                    Log.i("KMFG", "onDataChange: " + listUsers.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}