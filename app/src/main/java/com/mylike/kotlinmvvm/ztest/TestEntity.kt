package com.mylike.kotlinmvvm.ztest


/**
 * Created by ydh on 2022/7/6
 *
 */
class TestEntity(b: BaseImpl) : Base by b {
    val name: String by lazy(LazyThreadSafetyMode.NONE) {
        "姓名"
    }
    val age: String by lazy {
       " AgeBy()"
    }
}

