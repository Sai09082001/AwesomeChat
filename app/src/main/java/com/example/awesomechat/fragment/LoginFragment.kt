package com.example.awesomechat.fragment

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.KeyFileShare.FILE_NAME
import com.example.awesomechat.KeyFileShare.KEY_EMAIL
import com.example.awesomechat.R
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.model.Users
import com.example.awesomechat.viewmodel.LoginViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {
    private lateinit var edtEmail: AppCompatEditText
    private lateinit var edtPassword: AppCompatEditText
   // private var databaseRef: DatabaseReference? = null
    private var listUsers: ArrayList<Users>? = null
    private var auth: FirebaseAuth? = null
    override fun initViews() {
        listUsers = arrayListOf()
        checkLoginUser()
        auth = FirebaseAuth.getInstance()
        edtEmail = findViewById<AppCompatEditText>(R.id.edt_your_email)!!
        edtPassword = findViewById<AppCompatEditText>(R.id.edt_your_password)!!
   //     databaseRef = FirebaseDatabase.getInstance().reference.child("users")
        binding!!.tvLogin.setOnClickListener(View.OnClickListener {
          if(isLoginSuccess()) {
              savePref(KEY_EMAIL,edtEmail.text.toString())
              NavHostFragment.findNavController(this).navigate(R.id.homeMessageFragment)
          }
           //  NavHostFragment.findNavController(this).navigate(R.id.homeMessageFragment)
        })

//            checkDataUsers()
//            for (i in listUsers!!.indices) {
//                if (listUsers!![i].mail.equals(edtEmail.text.toString())
//                    &&  listUsers!![i].password.equals(edtPassword.text.toString())) {

        binding!!.tvRegisterNow.setOnClickListener({
            NavHostFragment.findNavController(this).navigate(R.id.registerFragment)
        })
    }

    private fun isLoginSuccess(): Boolean {
        listUsers!!.forEach {
           if(it.mail!!.equals(edtEmail.text.toString()) && it.password!!.equals(edtPassword.text.toString())) {
               return  true
           }
        }
        return false
    }

    fun checkLoginUser() {
        val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    val date = postSnapshot.child("date").getValue()
                    val email = postSnapshot.child("email").getValue()
                    val phone = postSnapshot.child("phone").getValue()
                    val password = postSnapshot.child("password").getValue()
                    val profileImage = postSnapshot.child("profileImage").getValue()
                    val isMe = postSnapshot.child("me").getValue()
                    val request = postSnapshot.child("request").getValue()
                    val username = postSnapshot.child("username").getValue()
                    val user = Users(username.toString(), email.toString(),password.toString(),date.toString(),phone.toString(),
                        profileImage.toString() , isMe.toString(),
                        request as ArrayList<String>?
                    )
                    listUsers!!.add(user)
                    Log.i("OOK", "onDataChange: "+email+password)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }
    fun savePref(key: String?, value: String?) {
        val pref: SharedPreferences = requireContext().
        getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        pref.edit().putString(key, value).apply()
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

//    private fun checkDataUsers() {
//        databaseRef!!.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                listUsers!!.clear()
//                val keys: MutableList<String?> = ArrayList()
//                for (keyNote in dataSnapshot.children) {
//                    keys.add(keyNote.key)
//                    val user = keyNote.getValue(Users::class.java)
//                    listUsers!!.add(user!!)
//                    Log.i("KMFG", "onDataChange: " + listUsers.toString())
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
//    }
}