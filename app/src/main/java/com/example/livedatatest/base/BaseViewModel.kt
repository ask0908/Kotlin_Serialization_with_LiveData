package com.example.livedatatest.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    private val loading = MutableLiveData(false)
    val loadingLiveData: LiveData<Boolean>
        get() = loading

    fun showLoading(show: Boolean) = loading.postValue(show)
}