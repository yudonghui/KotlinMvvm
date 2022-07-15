package com.mylike.kotlinmvvm.beans

import java.util.*

/**
 * Created by ydh on 2022/7/5
 *
 */
class TokenEntity {
     var special_role: List<Map<String?, String?>?>? = null
     var token: String? = null
     var userInfo: UserInfoEntity? = null
     var authList: List<AuthList> = ArrayList()

    class AuthList {
        var auth: String? = null
        var text: String? = null
    }
}