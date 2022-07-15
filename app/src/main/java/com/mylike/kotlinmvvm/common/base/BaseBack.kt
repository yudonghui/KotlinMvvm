package com.mylike.kotlinmvvm.common.base

import com.google.gson.stream.MalformedJsonException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by ydh on 2022/7/5
 *
 */
abstract class BaseBack<T> : Callback<BaseEntity<T>> {
    protected abstract fun onSuccess(t: T)

    protected abstract fun onFailed(code: String?, msg: String?)
    protected open fun onSuccess(baseEntity: BaseEntity<T>?) {}

    override fun onResponse(call: Call<BaseEntity<T>>, response: Response<BaseEntity<T>>) {
        val baseEntity = response.body()
        if (response.isSuccessful && baseEntity != null) {
            onSuccess(baseEntity)
            when {
                baseEntity.code.equals("1000") -> { //成功
                    baseEntity.data?.let { onSuccess(it) }
                }
                baseEntity.code.equals("4001") -> { //token失效
                }
                baseEntity.code.equals("4002") -> { //踢出登录
                }
                baseEntity.code.equals("4000") -> { //后台异常
                    onFailed(baseEntity.code, baseEntity.msg)
                }
                else -> {
                    onFailed(baseEntity.code, baseEntity.msg)
                }
            }
        } else {
            onFailed(response.code().toString() + "", response.message())
        }

    }

    override fun onFailure(call: Call<BaseEntity<T>>, t: Throwable) {
        if (t is ConnectException) { //网络连接失败
            onFailed("", "")
        } else if (t is MalformedJsonException) {
            onFailed("", "")
        } else if (t is SocketTimeoutException) {
            onFailed("", "")
        } else if (t is UnknownHostException) {
        } else if (t is IOException && "Canceled" == t.message) { //在网络还没有结束的时候关闭了页面。取消
        } else { //网络请求关闭。或者其他异常
        }
    }
}