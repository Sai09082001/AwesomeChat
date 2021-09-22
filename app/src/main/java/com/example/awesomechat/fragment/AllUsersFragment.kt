package com.example.awesomechat.fragment

import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.awesomechat.R
import com.example.awesomechat.databinding.AllUsersFragmentBinding
import com.example.awesomechat.model.Users
import com.example.awesomechat.viewmodel.AllUsersViewModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.awesomechat.adapter.AllUsersAdapter
import com.google.firebase.database.*


class AllUsersFragment : BaseFragment<AllUsersFragmentBinding, AllUsersViewModel>() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var dataRef : DatabaseReference
    private lateinit var userAdapter: AllUsersAdapter
    private lateinit var listUsers : ArrayList<Users>

    override fun initViews() {
        Toast.makeText(context,"All Users Fragment", Toast.LENGTH_SHORT).show()
        binding!!.rvAllUsers.layoutManager = LinearLayoutManager(context)
        auth = FirebaseAuth.getInstance()
        firebaseUser = auth.currentUser!!
        dataRef = FirebaseDatabase.getInstance().reference.child("Users")
        val dividerItemDecoration = DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
        binding!!.rvAllUsers.addItemDecoration(dividerItemDecoration)
        listUsers = arrayListOf()
        userAdapter = AllUsersAdapter(requireContext(),listUsers)
        binding!!.rvAllUsers.adapter = userAdapter
        loadAllUsers()
    }

    private fun loadAllUsers() {
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    var date = postSnapshot.child("date").getValue()
                    var name = postSnapshot.child("name").getValue()
                    var phone = postSnapshot.child("phone").getValue()
                    var profileImage = postSnapshot.child("profileImage").getValue()
                    var status = postSnapshot.child("status").getValue()
                    var user = Users(name.toString(), date.toString(),phone.toString(),profileImage.toString() ,status.toString())
                     listUsers.add(user!!)
                    Log.i("KMFG", "onDataChange: "+profileImage.toString())
                }
                userAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }


    override fun initBinding(mRootView: View): AllUsersFragmentBinding? {
        return AllUsersFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<AllUsersViewModel> {
        return AllUsersViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.all_users_fragment
    }
}