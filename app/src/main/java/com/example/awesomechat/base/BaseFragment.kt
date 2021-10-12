package com.example.awesomechat.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<K : ViewDataBinding> : Fragment(),
    View.OnClickListener {
    lateinit var mRootView: View
    var mData: Any? = null
    protected lateinit var binding: K

    protected abstract fun getVM(): ViewModel

    companion object {
        const val SYS_ERROR: String = "Có lỗi xảy ra!"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getLayoutId(), container, false)
        binding = initBinding(mRootView)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun showNotify(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun showNotify(text: Int) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        // do something
    }


    abstract fun initViews()

    abstract fun initBinding(mRootView: View): K

    protected abstract fun getLayoutId(): Int
}