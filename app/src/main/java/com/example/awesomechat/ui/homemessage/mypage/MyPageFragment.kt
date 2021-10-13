package com.example.awesomechat.ui.homemessage.mypage

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.MyPageFragmentBinding
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.utils.KeyFileShare
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyPageFragment : BaseFragment<MyPageFragmentBinding>() {

    @Inject
    lateinit var appNavigation: AppNavigation

    override fun initViews() {
        //viewModel.isDataUserChange()
        binding.ivToEditProfile.setOnClickListener(View.OnClickListener {
            appNavigation.openProfileToEditProfileScreen()
        })
        binding.tvLogout.setOnClickListener(View.OnClickListener {
            clearPref(KeyFileShare.KEY_EMAIL)
            NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
        })
        //viewModel.isDataUserChange()
        viewModel.email.observe(viewLifecycleOwner){
            binding.tvGmail.text = it.toString()
        }
        viewModel.userName.observe(viewLifecycleOwner){
            binding.tvName.text = it.toString()
        }
        viewModel.uriImage.observe(viewLifecycleOwner){
            Glide.with(requireContext()).load(it).into(binding.ivProfile)
        }
    }

    fun getPref(key: String?): String? {
        val pref: SharedPreferences = requireContext()
            .getSharedPreferences(KeyFileShare.FILE_NAME, Context.MODE_PRIVATE)
        return pref.getString(key, null)
    }


    override fun initBinding(mRootView: View): MyPageFragmentBinding {
        return MyPageFragmentBinding.bind(mRootView)
    }

    override fun onResume() {
        super.onResume()
        viewModel.isDataUserChange()
    }

    override fun getLayoutId(): Int {
        return R.layout.my_page_fragment
    }

    fun clearPref(key: String?) {
        val pref: SharedPreferences = requireContext()
            .getSharedPreferences(KeyFileShare.FILE_NAME, Context.MODE_PRIVATE)
        pref.edit().remove(key).apply()
    }

    override fun getVM(): MyPageViewModel = viewModel
    val viewModel: MyPageViewModel by viewModels()
}