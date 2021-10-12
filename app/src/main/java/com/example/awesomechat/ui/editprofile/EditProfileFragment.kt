package com.example.awesomechat.ui.editprofile

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.EditProfileFragmentBinding
import com.example.awesomechat.utils.KeyFileShare
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<EditProfileFragmentBinding>() {
    private val REQUEST_CODE: Int = 101
    private lateinit var uriImage: Uri
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    private lateinit var dataRef: DatabaseReference
    private lateinit var storageRef: StorageReference
    private lateinit var edtName: AppCompatEditText
    private lateinit var edtPhone: AppCompatEditText
    private lateinit var edtDate: AppCompatEditText

    val mActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            object : ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult?) {
                    if (result!!.resultCode == RESULT_OK) {
                        val intent = result.data ?: return
                        uriImage = intent.data!!
                        try {
                            Glide.with(requireContext()).load(uriImage.toString())
                                .into(binding.ivProfile)

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            })

    override fun initViews() {
        auth = FirebaseAuth.getInstance()
        viewModel.loadAllUsers()
        dataRef = FirebaseDatabase.getInstance().reference.child("Users")
//        edtName = findViewById<AppCompatEditText>(R.id.edt_name)!!
//        edtPhone = findViewById<AppCompatEditText>(R.id.edt_phone)!!
//        edtDate = findViewById<AppCompatEditText>(R.id.edt_date)!!
        binding.ivProfile.setOnClickListener(View.OnClickListener {
            onClickSelectPicture()
        })
        binding.tvSave.setOnClickListener(View.OnClickListener {
            updateDataUser()
            // saveDataEdit(edtName.text.toString(),edtPhone.text.toString(),edtDate.text.toString() );
        })
        getUserInformation()
    }

    private fun updateDataUser() {
        val user = auth.currentUser

        val profileUpdates =
            UserProfileChangeRequest.Builder().setDisplayName(binding.edtName.text.toString())
                .setPhotoUri(uriImage).build()

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun onClickSelectPicture() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions( //Method of Fragment
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        } else {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }

    fun getPref(key: String?): String? {
        val pref: SharedPreferences = requireContext()
            .getSharedPreferences(KeyFileShare.FILE_NAME, Context.MODE_PRIVATE)
        return pref.getString(key, null)
    }

    private fun getUserInformation() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            return
        }
        user.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl
            val phone = user.phoneNumber
            Glide.with(requireContext()).load(photoUrl).into(binding.ivProfile)
            binding.edtName.setText(name.toString())
            binding.edtPhone.setText(email.toString())
            // Check if user's email is verified
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
        val hashMap: HashMap<String, Any> = HashMap<String, Any>()

        // put() function
        hashMap.put("name", edtName)
        hashMap.put("phone", edtPhone)
        hashMap.put("date", edtDate)
        hashMap.put("profileImage", uriImage.toString())
        val dataRef = FirebaseDatabase.getInstance().reference.child("Users")
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    if (postSnapshot.child("email").value!!.equals(getPref(KeyFileShare.KEY_EMAIL))) {
                        dataRef.child(postSnapshot.key.toString())
                            .updateChildren(hashMap as Map<String, Any>,
                                DatabaseReference.CompletionListener { error, ref ->
                                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    openGallery()
                } else return
            }

        }
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!= null){
//            uriImage = data.data!!
//            binding!!.ivProfile.setImageURI(uriImage)
//        }
//    }

    override fun initBinding(mRootView: View): EditProfileFragmentBinding {
        return EditProfileFragmentBinding.bind(mRootView)
    }


    override fun getLayoutId(): Int {
        return R.layout.edit_profile_fragment
    }


    val viewModel: EditProfileViewModel by viewModels()

    override fun getVM(): EditProfileViewModel = viewModel

}
