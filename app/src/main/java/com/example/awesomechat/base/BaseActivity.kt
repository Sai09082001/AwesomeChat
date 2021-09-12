package com.example.awesomechat.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint


abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {
    protected var binding: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView: View = LayoutInflater.from(this).inflate(getLayoutId(), null)
        setContentView(rootView)
        binding = initBinding(rootView)
        initViews()
    }

    abstract fun initViews()

    abstract fun initBinding(rootView: View): V?

    abstract fun getLayoutId(): Int

    protected final fun showNotify(sms: String) {
        Toast.makeText(this, sms, Toast.LENGTH_SHORT).show()
    }

}