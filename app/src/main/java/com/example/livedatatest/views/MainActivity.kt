package com.example.livedatatest.views

import android.os.Bundle
import com.example.livedatatest.R
import com.example.livedatatest.databinding.ActivityMainBinding
import com.example.livedatatest.views.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind {
            //
        }
    }
}