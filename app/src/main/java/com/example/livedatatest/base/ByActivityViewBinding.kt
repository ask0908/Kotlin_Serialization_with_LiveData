package com.example.livedatatest.base

import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T: ViewBinding> AppCompatActivity.viewBinding(initializer: (LayoutInflater) -> T) {
    ViewBindingPropertyDelegate(this, initializer)
}

class ViewBindingPropertyDelegate<T: ViewBinding>(
    private val activity: AppCompatActivity,
    private val initializer: (LayoutInflater) -> T
): ReadOnlyProperty<AppCompatActivity, T>, LifecycleObserver {
    private var binding: T? = null

    init {
        activity.lifecycle.addObserver(this)
    }

    @Suppress("Unused")
    fun onCreate() {
        if (binding == null) {
            binding = initializer(activity.layoutInflater)
        }
        activity.setContentView(binding?.root!!)
        activity.lifecycle.removeObserver(this)
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (binding == null) {
            // 이건 메인 쓰레드에서만 있어야 한다
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw IllegalThreadStateException("이것은 다른 쓰레드에서 호출할 수 없습니다. 메인 쓰레드에만 있어야 합니다")
            }
            binding = initializer(thisRef.layoutInflater)
        }

        return binding!!
    }

}