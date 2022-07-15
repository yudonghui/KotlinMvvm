package com.mylike.kotlinmvvm.ui.model

import com.mylike.kotlinmvvm.common.base.BaseBack
import com.mylike.kotlinmvvm.common.base.BaseEntity
import com.mylike.kotlinmvvm.common.base.BaseModel
import com.mylike.kotlinmvvm.common.base.BasePageEntity
import com.mylike.kotlinmvvm.core.HttpClient
import com.mylike.kotlinmvvm.ui.bean.InfoEntity
import com.mylike.kotlinmvvm.ui.bean.MessageEntity

/**
 * Created by ydh on 2022/7/5
 *
 */
class MainModel : BaseModel() {
    fun getUserInfo(map: Map<String, Any>, back: BaseBack<InfoEntity>) {
        var call = HttpClient.getHttpApi().getUserInfo(HttpClient.getRequestBody(map))
        call.enqueue(back)
    }
    fun getMessageList(map: Map<String, Any>, back: BaseBack<BasePageEntity<MessageEntity>>) {
        var call = HttpClient.getHttpApi().getMessageList(HttpClient.getRequestBody(map))
        call.enqueue(back)
    }
}