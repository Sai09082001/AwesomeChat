package com.example.awesomechat.ui.homemessage.mypage.editprofile

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.awesomechat.R
import com.example.awesomechat.base.BaseFragment
import com.example.awesomechat.databinding.EditProfileFragmentBinding
import com.example.awesomechat.utils.KeyFileShare
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<EditProfileFragmentBinding>() {
    private val REQUEST_CODE: Int = 101
    private lateinit var uriImage: Uri

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

        binding.ivProfile.setOnClickListener(View.OnClickListener {
            onClickSelectPicture()
        })
        binding.tvSave.setOnClickListener(View.OnClickListener {
           // updateDataUser()
            viewModel.checkDataEdit(uriImage,binding.edtName.text.toString(),
                binding.edtPhone.text.toString(),binding.edtDate.text.toString())
        })
        viewModel.isDataAvaible.observe(viewLifecycleOwner){
            if(it){
                viewModel.updateDataUser(uriImage,binding.edtName.text.toString(),
                    binding.edtPhone.text.toString(),binding.edtDate.text.toString())
            }
        }
        getUserInformation()
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
        val user = FirebaseAuth.getInstance().currentUser ?: return
        user.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            uriImage = user.photoUrl!!
            val phone = user.phoneNumber
            Glide.with(requireContext()).load(uriImage).into(binding.ivProfile)
            binding.edtName.setText(name.toString())
            binding.edtPhone.setText(email.toString())
            // Check if user's email is verified
        }

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

    override fun initBinding(mRootView: View): EditProfileFragmentBinding {
        return EditProfileFragmentBinding.bind(mRootView)
    }


    override fun getLayoutId(): Int {
        return R.layout.edit_profile_fragment
    }


    val viewModel: EditProfileViewModel by viewModels()

    override fun getVM(): EditProfileViewModel = viewModel

}
