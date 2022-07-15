package com.mylike.kotlinmvvm.common.base

/**
 * Created by ydh on 2022/7/5
 *
 */
class BaseEntity<T> {
    var code: String? = null
    var msg: String? = null
    var data: T? = null
    var total: String? = null
    var totalPage = 0
}