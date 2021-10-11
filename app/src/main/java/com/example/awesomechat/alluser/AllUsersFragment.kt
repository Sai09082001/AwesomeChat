package com.example.awesomechat.alluser

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.awesomechat.R
import com.example.awesomechat.adapter.AllUsersAdapter
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.AllUsersFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AllUsersFragment : BaseFragment<AllUsersFragmentBinding, AllUsersViewModel>() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var userAdapter: AllUsersAdapter

    override fun initViews() {
        Toast.makeText(context, "All Users Fragment", Toast.LENGTH_SHORT).show()

        auth = FirebaseAuth.getInstance()
        firebaseUser = auth.currentUser!!

        binding.rvAllUsers.layoutManager = LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.rvAllUsers.addItemDecoration(dividerItemDecoration)
        subcriData()
    }

    private fun subcriData() {
        mViewModel.loadAllUsers()
        mViewModel.listUsers.observe(viewLifecycleOwner, Observer {
            // listUsers.addAll(mViewModel!!.listUsers.value!!)
            it.let {
                userAdapter = AllUsersAdapter(requireContext(), it)
                binding.rvAllUsers.adapter = userAdapter
            }
        })
    }

//    private fun loadAllUsers() {
//       // listUsers.removeAll(listUsers)
//        dataRef.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (postSnapshot in dataSnapshot.children) {
//                    var date = postSnapshot.child("date").getValue()
//                    var name = postSnapshot.child("name").getValue()
//                    var phone = postSnapshot.child("phone").getValue()
//                    var profileImage = postSnapshot.child("profileImage").getValue()
//                    var status = postSnapshot.child("status").getValue()
//                    var user = Users(name.toString(), date.toString(),phone.toString(),profileImage.toString() ,
//                        status as Boolean?
//                    )
//                     listUsers.add(user!!)
//                    Log.i("KMFG", "onDataChange: "+status.toString())
//                }
//                userAdapter.notifyDataSetChanged()
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//                // Getting Post failed, log a message
//                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
//                // ...
//            }
//        })
//    }


    override fun initBinding(mRootView: View): AllUsersFragmentBinding {
        return AllUsersFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<AllUsersViewModel> {
        return AllUsersViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.all_users_fragment
    }
}