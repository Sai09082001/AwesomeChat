package com.example.awesomechat.dialog



import android.os.Bundle

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.awesomechat.databinding.AlertDialogBinding
import com.example.awesomechat.utils.ParamIntent


class AlertDialog : DialogFragment() {


    lateinit var binding: AlertDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AlertDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = requireArguments().getString(ParamIntent.PARAM_DATA)
        binding.tvNotification.text = data
    }

    companion object {
        val TAG = AlertDialog.javaClass.name

        //Được dùng khi khởi tạo dialog mục đích nhận giá trị
        fun newInstance(data: String?): AlertDialog {
            val dialog = AlertDialog()
            val args = Bundle()
            args.putString(ParamIntent.PARAM_DATA, data)
            dialog.setArguments(args)
            return dialog
        }
    }
}