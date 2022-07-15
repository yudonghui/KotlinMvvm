package com.mylike.kotlinmvvm.ui.model

import com.mylike.kotlinmvvm.beans.TokenEntity
import com.mylike.kotlinmvvm.common.base.BaseBack
import com.mylike.kotlinmvvm.common.base.BaseEntity
import com.mylike.kotlinmvvm.common.base.BaseModel
import com.mylike.kotlinmvvm.core.HttpClient
import retrofit2.Call

/**
 * Created by ydh on 2022/7/5
 *
 */
class LoginModel : BaseModel() {
    fun getLogin(map: Map<String, Any>, baseBack: BaseBack<TokenEntity>) {
        var call: Call<BaseEntity<TokenEntity>> =
            HttpClient.getHttpApi().getNewLogin(HttpClient.getRequestBody(map))
        call.enqueue(baseBack)
    }
}