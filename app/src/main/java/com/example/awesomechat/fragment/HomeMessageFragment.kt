package com.example.awesomechat.fragment

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.awesomechat.R
import com.example.awesomechat.databinding.HomeMessageFragmentBinding
import com.example.awesomechat.viewmodel.HomeMessageViewModel

class HomeMessageFragment : BaseFragment<HomeMessageFragmentBinding, HomeMessageViewModel>() {
    override fun initViews() {
        Toast.makeText(context,"Oki",Toast.LENGTH_SHORT).show()
        binding!!.layoutBottomBar.ivToMyPage.setOnClickListener(View.OnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.myPageFragment)
        })
    }

    override fun initBinding(mRootView: View): HomeMessageFragmentBinding? {
        return HomeMessageFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<HomeMessageViewModel> {
        return HomeMessageViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.home_message_fragment
    }


}