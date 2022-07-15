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
        var testEntity = TestEntity()
        testEntity.let {
            it.add()
            10
        }
        testEntity.also {
            it.age = 9
        }.age = 10
        println("also之后 ${testEntity.age}")
        testEntity.run {

        }
        testEntity.apply {

        }.age=12
    }
}