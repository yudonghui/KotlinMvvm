package com.mylike.kotlinmvvm.haohao.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @description
 * @author: created by peihao.feng
 * @date: 2022/7/9
 */
class LoginViewModel: ViewModel() {

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
}