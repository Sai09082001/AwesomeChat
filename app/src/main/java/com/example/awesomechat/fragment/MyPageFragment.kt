package com.example.awesomechat.fragment

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.databinding.MyPageFragmentBinding
import com.example.awesomechat.viewmodel.MyPageViewModel

class MyPageFragment :  BaseFragment<MyPageFragmentBinding, MyPageViewModel>() {
    override fun initViews() {
        Toast.makeText(context,"Oki", Toast.LENGTH_SHORT).show()
        binding!!.ivToEditProfile.setOnClickListener(View.OnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.editProfileFragment)
        })
    }

    override fun initBinding(mRootView: View): MyPageFragmentBinding? {
        return MyPageFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<MyPageViewModel> {
        return MyPageViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.my_page_fragment
    }


}