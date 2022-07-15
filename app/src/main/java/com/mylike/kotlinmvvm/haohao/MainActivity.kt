package com.mylike.kotlinmvvm.haohao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.databinding.ActivityMainBinding
import com.mylike.kotlinmvvm.databinding.ActivityMainhBinding
import com.mylike.kotlinmvvm.haohao.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainhBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mainh)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.activity = this

        viewModel.getMainData()
    }

    private fun initObserver(){
    }

    fun gotoLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
}