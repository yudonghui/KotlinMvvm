package com.mylike.kotlinmvvm.core

import com.mylike.kotlinmvvm.beans.TokenEntity
import com.mylike.kotlinmvvm.common.base.BaseEntity
import com.mylike.kotlinmvvm.common.base.BasePageEntity
import com.mylike.kotlinmvvm.ui.bean.InfoEntity
import com.mylike.kotlinmvvm.ui.bean.MessageEntity
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by ydh on 2022/7/5
 *
 */
interface ServersApi {
    //登录
    @POST("his-api/v1.0/app/login")
    fun getNewLogin(@Body body: RequestBody): Call<BaseEntity<TokenEntity>>

    //消息列表
    @POST("mylike-crm/api/message/getMessageList.do")
    fun getMessageList(@Body body: RequestBody): Call<BaseEntity<BasePageEntity<MessageEntity>>>

    //客户信息
    @POST("his-api/v1.0/cus/getPatientInfo")
    fun getUserInfo(@Body body: RequestBody): Call<BaseEntity<InfoEntity>>
}