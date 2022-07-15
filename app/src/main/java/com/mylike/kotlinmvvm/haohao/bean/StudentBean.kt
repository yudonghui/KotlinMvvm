package com.mylike.kotlinmvvm.haohao.bean

/**
 * @description
 * @author: created by peihao.feng
 * @date: 2022/7/9
 */
data class StudentList(
    val list: ArrayList<StudentBean>? = null
) {

    data class StudentBean(
        val name: String? = null,
        val age: Int? = null,
        val address: String? = null
    )
}