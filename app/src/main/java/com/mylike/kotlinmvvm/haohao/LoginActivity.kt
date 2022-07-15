package com.mylike.kotlinmvvm.haohao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.databinding.ActivityLoginBinding
import com.mylike.kotlinmvvm.databinding.ActivityLoginhBinding
import com.mylike.kotlinmvvm.haohao.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginhBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loginh)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.activity = this
    }

    fun login(){
        if(!viewModel.userName.value.isNullOrEmpty() && !viewModel.password.value.isNullOrEmpty()){
            startActivity(Intent(this,ListActivity::class.java))
        }
    }
}