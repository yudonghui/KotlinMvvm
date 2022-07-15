package com.mylike.kotlinmvvm.ztest

import com.mylike.kotlinmvvm.utils.LogUtils

/**
 * Created by ydh on 2022/7/6
 *
 */
open class BaseImpl : Base {
    override fun print() {
        LogUtils.e("BaseImpl 的print方法")
    }
}