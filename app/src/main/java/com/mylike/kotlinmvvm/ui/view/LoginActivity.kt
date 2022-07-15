package com.mylike.kotlinmvvm.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.jaeger.library.StatusBarUtil
import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.common.SPUtils
import com.mylike.kotlinmvvm.common.base.BaseMvvmActivity
import com.mylike.kotlinmvvm.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var mViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        //更改状态栏颜色
        StatusBarUtil.setColor(
            this,
            ContextCompat.getColor(this, R.color.color_theme),
            0
        )
        setContentView(R.layout.activity_login)
        var cacheStr = SPUtils.getCacheStr(SPUtils.FILE_USER, SPUtils.TOKEN)
        if (!TextUtils.isEmpty(cacheStr)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        mViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    fun login(view: View) {
        var map = mutableMapOf<String, Any>()
        map["mobileNo"] = "13162821162"
        map["deviceId"] = "864779030729671"
        map["password"] = "123456"
        map["appVersion"] = "2.5.9"
        map["registrationId"] = "160a3797c84fda5340a"
        map["phoneType"] = "android"
        map["deviceInfo"] = "品牌:HONOR；手机型号:CAM-AL00；系统版本:6.0；APP版本:"
        mViewModel?.getLogin(map, this)
    }

    fun success() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}