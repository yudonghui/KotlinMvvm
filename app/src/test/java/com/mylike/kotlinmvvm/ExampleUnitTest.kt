package com.mylike.kotlinmvvm

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        var a: String? = null
        var toInt = a?.toInt() ?: 0
        println("结果：${toInt}")
    }
}