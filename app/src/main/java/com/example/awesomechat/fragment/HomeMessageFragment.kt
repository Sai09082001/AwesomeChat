package com.example.awesomechat.fragment

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.awesomechat.R
import com.example.awesomechat.adapter.FriendPagerAdapter
import com.example.awesomechat.adapter.HomePagerAdapter
import com.example.awesomechat.databinding.HomeMessageFragmentBinding
import com.example.awesomechat.viewmodel.HomeMessageViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeMessageFragment : BaseFragment<HomeMessageFragmentBinding, HomeMessageViewModel>() {
    private lateinit var homePagerAdapter: HomePagerAdapter
    override fun initViews() {
        homePagerAdapter = HomePagerAdapter(requireActivity())
        binding!!.vpHomeMessage.adapter = homePagerAdapter
        binding!!.vpHomeMessage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> binding!!.layoutBottomBar.menu.findItem(R.id.menu_message).setChecked(true)
                    1 -> binding!!.layoutBottomBar.menu.findItem(R.id.menu_friend).setChecked(true)
                    2 -> binding!!.layoutBottomBar.menu.findItem(R.id.menu_my_page).setChecked(true)
                }
            }
        })
        binding!!.layoutBottomBar.setOnItemSelectedListener(object : NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.menu_message -> binding!!.vpHomeMessage.setCurrentItem(0)
                    R.id.menu_friend -> binding!!.vpHomeMessage.setCurrentItem(1)
                    R.id.menu_my_page -> binding!!.vpHomeMessage.setCurrentItem(2)
                }
                return true
            }

        })
//        binding!!.layoutBottomBar.ivToMyPage.setOnClickListener(View.OnClickListener {
//            NavHostFragment.findNavController(this).navigate(R.id.myPageFragment)
//        })
//        binding!!.layoutBottomBar.ivToFriend.setOnClickListener(View.OnClickListener {
//            NavHostFragment.findNavController(this).navigate(R.id.friendFragment)
//        })
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