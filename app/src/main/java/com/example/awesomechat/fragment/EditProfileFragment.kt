package com.example.awesomechat.fragment

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.number.NumberFormatter.with
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.awesomechat.KeyFileShare
import com.example.awesomechat.R
import com.example.awesomechat.databinding.EditProfileFragmentBinding
import com.example.awesomechat.model.Users
import com.example.awesomechat.viewmodel.EditProfileViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment :  BaseFragment<EditProfileFragmentBinding, EditProfileViewModel>() {
    private val REQUEST_CODE: Int = 101
    private lateinit var uriImage : Uri
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var dataRef :DatabaseReference
    private lateinit var storageRef :StorageReference
    private lateinit var edtName : AppCompatEditText
    private lateinit var edtPhone : AppCompatEditText
    private lateinit var edtDate : AppCompatEditText

    override fun initViews() {
        auth = FirebaseAuth.getInstance()
        mViewModel!!.loadAllUsers()
        dataRef =FirebaseDatabase.getInstance().reference.child("Users")
         edtName = findViewById<AppCompatEditText>(R.id.edt_name)!!
         edtPhone = findViewById<AppCompatEditText>(R.id.edt_phone)!!
         edtDate = findViewById<AppCompatEditText>(R.id.edt_date)!!
        binding!!.ivProfile.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent,REQUEST_CODE)
        })
        binding!!.tvSave.setOnClickListener(View.OnClickListener {
            saveDataEdit(edtName.text.toString(),edtPhone.text.toString(),edtDate.text.toString() );
        })
       setProfileUser()
    }

    fun getPref(key: String?): String? {
        val pref: SharedPreferences = requireContext()
            .getSharedPreferences(KeyFileShare.FILE_NAME, Context.MODE_PRIVATE)
        return pref.getString(key, null)
    }

    private fun setProfileUser() {
        mViewModel!!.listUsers.observe(viewLifecycleOwner, Observer {
            it.forEach{
                if(it.mail.equals(getPref(KeyFileShare.KEY_EMAIL))){
                    Glide.with(requireContext()).load(it.profileImage.toString()).into(binding!!.ivProfile)
                    binding!!.edtName.setText(it.userName.toString())
                    binding!!.edtDate.setText(it.date.toString())
                    binding!!.edtPhone.setText(it.phone.toString())
                }
            }
        })
    }


    private fun saveDataEdit(edtName: String, edtPhone: String, edtDate: String) {
        if (TextUtils.isEmpty(edtName)) {
            Toast.makeText(context, "Yêu cầu họ tên ", Toast.LENGTH_SHORT).show()
        }
        if (TextUtils.isEmpty(edtPhone)) {
            Toast.makeText(context, "Yêu cầu SĐT", Toast.LENGTH_SHORT).show()
        }

        if (TextUtils.isEmpty(edtDate)) {
            Toast.makeText(context, "Yêu cầu ngày tháng năm sinh", Toast.LENGTH_SHORT).show()
        }
        val hashMap : HashMap<String, Any>
                = HashMap<String, Any> ()

        // put() function
        hashMap.put("name" , edtName)
        hashMap.put("phone" , edtPhone)
        hashMap.put("date" , edtDate)
        hashMap.put("profileImage",uriImage.toString())
        val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    if(postSnapshot.child("email").value!!.equals(getPref(KeyFileShare.KEY_EMAIL))){
                        dataRef.child(postSnapshot.key.toString()).updateChildren(hashMap as Map<String, Any>,
                          DatabaseReference.CompletionListener { error, ref ->
                          Toast.makeText(context,"Done",Toast.LENGTH_SHORT).show()
                       })
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
//        dataRef.child(firebaseUser.uid).updateChildren(hashMap as Map<String, Any>,
//            DatabaseReference.CompletionListener { error, ref ->
//                Toast.makeText(context,"Done",Toast.LENGTH_SHORT).show()
//        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!= null){
            uriImage = data.data!!
            binding!!.ivProfile.setImageURI(uriImage)
        }
    }

    override fun initBinding(mRootView: View): EditProfileFragmentBinding {
        return EditProfileFragmentBinding.bind(mRootView)
    }

    override fun getViewModelClass(): Class<EditProfileViewModel> {
        return EditProfileViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.edit_profile_fragment
    }


}