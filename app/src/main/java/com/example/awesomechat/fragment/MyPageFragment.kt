package com.example.awesomechat.fragment

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.databinding.MyPageFragmentBinding
import com.example.awesomechat.viewmodel.MyPageViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint
import de.hdodenhof.circleimageview.CircleImageView

@AndroidEntryPoint
class MyPageFragment :  BaseFragment<MyPageFragmentBinding, MyPageViewModel>() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var dataRef : DatabaseReference
    private lateinit var ivProfile : CircleImageView
    override fun initViews() {
        ivProfile = findViewById<CircleImageView>(R.id.iv_profile)!!
        auth = FirebaseAuth.getInstance()
        firebaseUser = auth.currentUser!!
        dataRef = FirebaseDatabase.getInstance().reference.child("Users")
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

    override fun onStart() {
        super.onStart()
        if(firebaseUser!= null){
            dataRef.child(firebaseUser.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        Glide.with(context!!).load(snapshot.child("profileImage").value.toString()).into(ivProfile)
                        binding!!.tvName.setText(snapshot.child("name").value.toString())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

}