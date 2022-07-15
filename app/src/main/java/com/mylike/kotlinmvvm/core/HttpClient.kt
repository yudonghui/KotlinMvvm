package com.mylike.kotlinmvvm.core

import com.google.gson.Gson
import com.mylike.kotlinmvvm.utils.LogUtils
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ydh on 2022/7/5
 *
 */
object HttpClient {
    private const val BASE_URL = "http://crmapp.shmylike.cn/"
    private var serversApi: ServersApi

    init {

        val loggingInterceptor = HttpLoggingInterceptor { message ->
            if (message.contains("trace-id")) {
                LogUtils.e("返回结果: $message")
            }
            if (message.contains("resultCode") && message.contains("resultMsg") || message.contains(
                    "code"
                ) && message.contains("msg")
            ) {
                // LogUtils.e("请求结果" + message);
                LogUtils.e("请求结果$message")
            } else if (message.contains("-->") && message.contains("http://")) {
                LogUtils.e("请求接口$message")
            } else if (message.contains("{")) {
                LogUtils.e("请求参数$message")
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        var okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(RequestParamInterceptor())
            .connectTimeout(30000, TimeUnit.MILLISECONDS)
            .readTimeout(30000, TimeUnit.MILLISECONDS)
            .build()

        var retrofit: Retrofit =
            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        serversApi = retrofit.create(ServersApi::class.java)
    }

    fun getHttpApi(): ServersApi {
        return serversApi
    }

    fun getRequestBody(map: Map<String, Any>): RequestBody {
        var toJson = Gson().toJson(map)
        return RequestBody.create(
            okhttp3.MediaType.parse("application/json; charset=utf-8"),
            toJson
        )
    }
}