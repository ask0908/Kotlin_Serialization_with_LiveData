package com.example.livedatatest.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.livedatatest.R
import com.example.livedatatest.dialog.DialogModel

abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: VM

    private val progressDialog: AppCompatDialog? by lazy {
        AppCompatDialog(this, R.style.ProgressDialog).apply {
            setContentView(R.layout.dialog_loading)
            setCancelable(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        bindViewModel()

        viewModel.loadingLiveData.observe(this) {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        }
    }

    abstract fun provideViewModel(): VM

    abstract fun bindViewModel()

    protected fun showLoading() {
        progressDialog?.show()
    }

    protected fun hideLoading() {
        progressDialog?.dismiss()
    }

    fun showDialog(dialogModel: DialogModel) {
        dialogModel.run {
            AlertDialog.Builder(this@BaseActivity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton?.text) { dialog, _ ->
                    positiveButton?.task?.invoke()
                    dialog.dismiss()
                }.create()
        }.show()
    }
}