package com.example.awesomechat.fragment

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.awesomechat.R
import com.example.awesomechat.databinding.LoginFragmentBinding
import com.example.awesomechat.databinding.SplashFragmentBinding
import com.example.awesomechat.navigation.AppNavigation
import com.example.awesomechat.viewmodel.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashFragmentBinding, SplashViewModel>() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var dataRef : DatabaseReference
    @Inject
    lateinit var appNavigation: AppNavigation
    override fun initViews() {
        auth = FirebaseAuth.getInstance()
        dataRef = FirebaseDatabase.getInstance().reference.child("Users")
        Handler(Looper.getMainLooper()).postDelayed({
            if (auth.currentUser!= null){
                firebaseUser = auth.currentUser!!
                dataRef.child(firebaseUser.uid).addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            gotoHomeMessage()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
            }else{
                gotoLoginScreen()
            }

        }, 2000)
    //    NavHostFragment.findNavController(this).popBackStack(R.id.splashFragment, true)
    }

    private fun gotoHomeMessage() {
        NavHostFragment.findNavController(this).navigate(R.id.homeMessageFragment)
    }

    private fun gotoLoginScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.loginFragment)
    }

    override fun initBinding(mRootView: View): SplashFragmentBinding? {
       return SplashFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.splash_fragment
    }
}