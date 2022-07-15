package com.mylike.kotlinmvvm.haohao.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mylike.kotlinmvvm.haohao.bean.MainDataBean
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @description
 * @author: created by peihao.feng
 * @date: 2022/7/9
 */
class MainViewModel : ViewModel() {

    val text1 = MutableLiveData<String>()
    val text2 = MutableLiveData<String>()

    fun getMainData() {
        viewModelScope.launch {
            //模拟网络请求
            delay(2000)
            val mainDataBean = MainDataBean("Hello", "World", "Android", "iOS")
            text1.postValue(mainDataBean.text1 ?: "")
            text2.postValue(mainDataBean.text2 ?: "")
        }
    }
}