package com.example.awesomechat.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.databinding.EditProfileFragmentBinding
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
        firebaseUser = auth.currentUser!!
        dataRef =FirebaseDatabase.getInstance().reference.child("Users")
        storageRef = FirebaseStorage.getInstance().reference.child("ProfileImages")
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
    }

    override fun onStart() {
        super.onStart()
        if(firebaseUser!= null){
            dataRef.child(firebaseUser.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        Glide.with(context!!).load(snapshot.child("profileImage").value.toString()).into(binding!!.ivProfile)
                        edtName.setText(snapshot.child("name").value.toString())
                        edtPhone.setText(snapshot.child("phone").value.toString())
                        edtDate.setText(snapshot.child("date").value.toString())
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
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
        storageRef.child(firebaseUser.uid).putFile(uriImage).addOnCompleteListener(
            OnCompleteListener {
                if(it.isSuccessful){
                    storageRef.child(firebaseUser.uid).downloadUrl.addOnSuccessListener(
                        OnSuccessListener {
                            var hashMap : HashMap<String, String>
                                    = HashMap<String, String> ()

                            // put() function
                            hashMap.put("name" , edtName)
                            hashMap.put("phone" , edtPhone)
                            hashMap.put("date" , edtDate)
                            hashMap.put("profileImage",uriImage.toString())
                            hashMap.put("status","offline")
                            dataRef.child(firebaseUser.uid).updateChildren(hashMap as Map<String, Any>).addOnSuccessListener(
                                OnSuccessListener {
                                     Toast.makeText(context,"Done",Toast.LENGTH_SHORT).show()
                                }).addOnFailureListener(OnFailureListener {
                                     Toast.makeText(context,"Fail",Toast.LENGTH_SHORT).show()
                            })
                        })
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!= null){
            uriImage = data.data!!
            binding!!.ivProfile.setImageURI(uriImage)
        }
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