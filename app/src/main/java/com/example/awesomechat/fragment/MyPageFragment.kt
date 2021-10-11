package com.example.awesomechat.fragment

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.awesomechat.KeyFileShare
import com.example.awesomechat.R
import com.example.awesomechat.databinding.MyPageFragmentBinding
import com.example.awesomechat.viewmodel.MyPageViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<MyPageFragmentBinding, MyPageViewModel>() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    override fun initViews() {
        auth = FirebaseAuth.getInstance()
        firebaseUser = auth.currentUser!!
        binding!!.ivToEditProfile.setOnClickListener(View.OnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.editProfileFragment)
        })
        binding!!.tvLogout.setOnClickListener(View.OnClickListener {
            clearPref(KeyFileShare.KEY_EMAIL)
            NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
        })
        // setProfileUser()
        getUserInformation()
    }

    fun getPref(key: String?): String? {
        val pref: SharedPreferences = requireContext()
            .getSharedPreferences(KeyFileShare.FILE_NAME, Context.MODE_PRIVATE)
        return pref.getString(key, null)
    }

    private fun setProfileUser() {
        Log.i("OLL", "setProfileUser: " + mViewModel!!.listUsers.value)
        mViewModel!!.listUsers.observe(viewLifecycleOwner, Observer {
            it.forEach {
                if (it.mail.equals(getPref(KeyFileShare.KEY_EMAIL))) {
                    Glide.with(requireContext()).load(it.profileImage.toString())
                        .into(binding!!.ivProfile)
                    binding!!.tvName.text = it.userName.toString()
                    binding!!.tvGmail.text = it.mail.toString()
                }
            }
        })
//        mViewModel!!.listUsers.value!!.forEach {
//            if(it.mail.equals(getPref(KeyFileShare.KEY_EMAIL))){
//                Glide.with(requireContext()).load(it.profileImage.toString()).into(ivProfile)
//                binding!!.tvName.setText(it.userName.toString())
//                binding!!.tvGmail.setText(it.mail.toString())
//            }
//        }
    }

    private fun getUserInformation() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            return
        }
        user.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl
            val phone = user.phoneNumber
            Glide.with(requireContext()).load(photoUrl).into(binding!!.ivProfile)
            binding!!.tvName.text = name.toString()
            binding!!.tvGmail.text = email.toString()
            // Check if user's email is verified
        }

    }

    override fun initBinding(mRootView: View): MyPageFragmentBinding {
        return MyPageFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<MyPageViewModel> {
        return MyPageViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.my_page_fragment
    }

    fun clearPref(key: String?) {
        val pref: SharedPreferences = requireContext()
            .getSharedPreferences(KeyFileShare.FILE_NAME, Context.MODE_PRIVATE)
        pref.edit().remove(key).apply()
    }

}