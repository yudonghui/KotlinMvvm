package com.mylike.kotlinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mylike.kotlinmvvm.ui.bean.InfoEntity
import com.mylike.kotlinmvvm.utils.LogUtils
import com.mylike.kotlinmvvm.ztest.BaseImpl
import com.mylike.kotlinmvvm.ztest.TestEntity

class TestKtActivity : AppCompatActivity() {
    private lateinit var mLiveData: MutableLiveData<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_kt)
        mLiveData = MutableLiveData<String>()
        mLiveData.observe(this, {
            LogUtils.e(it)
        })
        LogUtils.e("生命周期：onCreate")
        mLiveData.value = "onCreate"
        var b = BaseImpl()
        TestEntity(b).print()
    }

    override fun onStart() {
        super.onStart()
        LogUtils.e("生命周期：onStart")
        mLiveData.value = "onStart"
    }

    override fun onRestart() {
        super.onRestart()
        LogUtils.e("生命周期：onRestart")
        mLiveData.value = "onRestart"
    }

    override fun onResume() {
        super.onResume()
        LogUtils.e("生命周期：onResume")
        mLiveData.value = "onResume"
    }

    override fun onPause() {
        super.onPause()
        LogUtils.e("生命周期：onPause")
        mLiveData.value = "onPause"
    }

    override fun onStop() {
        super.onStop()
        LogUtils.e("生命周期：onStop")
        mLiveData.value = "onStop"
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("生命周期：onDestroy")
        mLiveData.value = "onDestroy"
    }
}