package com.example.awesomechat.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.awesomechat.viewmodel.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

abstract class BaseFragment<K : ViewDataBinding, V : BaseViewModel> : Fragment(), View.OnClickListener{
    lateinit var mContext: Context
    lateinit var mRootView: View
    var mData: Any? = null
    protected var mViewModel: V ? =  null
    protected var binding: K? = null

    companion object {
        const val SYS_ERROR: String = "Có lỗi xảy ra!"
    }

    fun <T : View> findViewById(id: Int): T? {
        return findViewById(id, null)
    }

    fun <T : View> findViewById(id: Int, event: View.OnClickListener?): T? {
        val v: T? = mRootView.findViewById(id)
        if (event != null) {
            v?.setOnClickListener(event)
        }
        return v
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getLayoutId(), container, false)
        mViewModel = ViewModelProvider(this).get(getViewModelClass())
        binding = initBinding(mRootView)
        initViews()
        return mRootView
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

    abstract fun initBinding(mRootView: View): K?

    abstract fun getViewModelClass(): Class<V>

    protected abstract fun getLayoutId(): Int
}