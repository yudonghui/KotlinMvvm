package com.mylike.kotlinmvvm.ui.viewmodel

import com.mylike.kotlinmvvm.beans.TokenEntity
import com.mylike.kotlinmvvm.common.SPUtils
import com.mylike.kotlinmvvm.common.base.BaseBack
import com.mylike.kotlinmvvm.common.base.BaseViewModel
import com.mylike.kotlinmvvm.ui.model.LoginModel
import com.mylike.kotlinmvvm.ui.view.LoginActivity

/**
 * Created by ydh on 2022/7/5
 *
 */
class LoginViewModel : BaseViewModel() {
    private var mModel: LoginModel = LoginModel()

    fun getLogin(map: Map<String, Any>, loginActivity: LoginActivity) {
        mModel?.getLogin(map, object : BaseBack<TokenEntity>() {

            override fun onSuccess(t: TokenEntity) {
                t.token?.let { SPUtils.setCache(SPUtils.FILE_USER, SPUtils.TOKEN, it) } //token
                loginActivity?.success()
            }

            override fun onFailed(code: String?, msg: String?) {

            }

        })
    }
}