package com.mylike.kotlinmvvm

import com.mylike.kotlinmvvm.beans.UserInfoEntity
import com.mylike.kotlinmvvm.ui.bean.InfoEntity
import com.mylike.kotlinmvvm.ztest.TestEntity
import kotlin.reflect.KProperty

/**
 * Created by ydh on 2022/7/6
 *
 */
class UserInfoBy() {
    fun yud(name: String, age: (a: Int, b: Int) -> Int): String {
        return name + age(1, 2)
    }
}