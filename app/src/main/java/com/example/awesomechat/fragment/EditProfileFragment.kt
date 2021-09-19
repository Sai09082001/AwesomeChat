package com.example.awesomechat.fragment

import android.view.View
import android.widget.Toast
import com.example.awesomechat.R
import com.example.awesomechat.databinding.EditProfileFragmentBinding
import com.example.awesomechat.viewmodel.EditProfileViewModel
import com.example.awesomechat.viewmodel.HomeMessageViewModel

class EditProfileFragment :  BaseFragment<EditProfileFragmentBinding, EditProfileViewModel>() {
    override fun initViews() {
        Toast.makeText(context,"Oki", Toast.LENGTH_SHORT).show()
    }

    override fun initBinding(mRootView: View): EditProfileFragmentBinding? {
        return EditProfileFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<EditProfileViewModel> {
        return EditProfileViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.edit_profile_fragment
    }


}