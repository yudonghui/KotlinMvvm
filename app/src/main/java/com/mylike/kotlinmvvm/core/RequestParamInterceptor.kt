package com.mylike.kotlinmvvm.core

import com.mylike.kotlinmvvm.common.SPUtils
import com.mylike.kotlinmvvm.utils.AppUtils
import com.mylike.kotlinmvvm.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by ydh on 2022/7/5
 *
 */
class RequestParamInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var token = SPUtils.getCacheStr(SPUtils.FILE_USER, SPUtils.TOKEN)
        LogUtils.e("token: $token")
        var authorised =
            request.newBuilder().header("Content-type", "application/json;charset=UTF-8")
                .header("token", token)
                .header("source", "Android")
                .header("ml_version", AppUtils.getVersionName())
                .build()
        return chain.proceed(authorised)
    }
}