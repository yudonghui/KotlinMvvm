package com.mylike.kotlinmvvm.common.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.telecom.Call
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.jaeger.library.StatusBarUtil
import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.common.NetBroadcastReceiver
import com.mylike.kotlinmvvm.common.NetBroadcastReceiver.NetWorkCallBack
import com.mylike.kotlinmvvm.utils.NetWorkUtils
import java.util.*

/**
 * @author yudonghui
 * @date 2022/7/5
 */
abstract class BaseMvvmActivity : AppCompatActivity(), BaseView,
    NetWorkCallBack {

    /**
     * 是否显示加载框
     */
    private var isLoadProgress = true

    /**
     * 是否隐藏标题栏
     */
    private var isActionBar = true

    /**
     * 是否禁止键盘弹出
     */
    private var isKeyboardUp = true

    /**
     * 是否禁止横屏 手机
     */
    private var isScreenRotating = true

    /**
     * 是否显示悬浮球
     */
    private var isFloatingDragger = false

    /**
     * 是否更改状态栏颜色
     */
    private var isStatusBarColor = true

    /**
     * 列表页脚
     * 有列表的activity可方便显示页脚
     */
    private val textView: TextView? = null
    protected var mContext: Context? = null

    /**
     * 管理所有的网络请求
     */
    var mNetWorkList: List<Call> = ArrayList()
    private var mNetBroadcastReceiver: NetBroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏
        if (isActionBar) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        initDataBinding()
        mContext = this
        init()
    }

    protected abstract fun initDataBinding()
    private fun init() {
        setStatusBar()
        //动态接受网络变化的广播接收器
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        mNetBroadcastReceiver = NetBroadcastReceiver(this)
        registerReceiver(mNetBroadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        unregisterReceiver(mNetBroadcastReceiver)
        super.onDestroy()
    }

    private fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        } else {
        }
    }

    @SuppressLint("NewApi")
    override fun setContentView(layoutResID: Int) {
        //更改状态栏颜色
        if (isStatusBarColor) {
            StatusBarUtil.setColor(
                this,
                ContextCompat.getColor(this, R.color.color_theme),
                0
            )
        }
        super.setContentView(layoutResID)
    }

    /**
     * --------------------------------设置是否显示加载框-------------------------------------------
     *
     * @param isLoadProgress 默认显示
     */
    fun setLoadProgress(isLoadProgress: Boolean) {
        this.isLoadProgress = isLoadProgress
    }

    /**
     * --------------------------------设置是否隐藏标题栏-------------------------------------------
     *
     * @param isActionBar 默认隐藏
     */
    fun setActionBar(isActionBar: Boolean) {
        this.isActionBar = isActionBar
    }

    /**
     * --------------------------------设置是否更改状态栏颜色---------------------------------------
     *
     * @param isStatusBarColor 默认状态栏颜色与标题颜色一致
     */
    fun setStatusBarColor(isStatusBarColor: Boolean) {
        this.isStatusBarColor = isStatusBarColor
    }

    /**
     * --------------------------------设置是否禁止键盘弹出-----------------------------------------
     *
     * @param isKeyboardUp 默认显示
     */
    fun setKeyboardUp(isKeyboardUp: Boolean) {
        this.isKeyboardUp = isKeyboardUp
    }

    /**
     * --------------------------------设置是否禁止横屏---------------------------------------------
     *
     * @param isScreenRotating 默认禁止横屏
     */
    fun setScreenRotating(isScreenRotating: Boolean) {
        this.isScreenRotating = isScreenRotating
    }

    /**
     * --------------------------------设置是否显示悬浮球-------------------------------------------
     *
     * @param isFloatingDragger 默认隐藏悬浮球
     */
    fun setFloatingDragger(isFloatingDragger: Boolean) {
        this.isFloatingDragger = isFloatingDragger
    }

    /**
     * 跳转页面
     *
     * @param clz 跳转的activity
     */
    fun startActivity(clz: Class<*>?) {
        startActivity(Intent(this@BaseMvvmActivity, clz))
    }

    /**
     * 携带数据的页面跳转
     *
     * @param clz    跳转的activity
     * @param bundle 数据
     */
    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent()
        intent.setClass(this, clz!!)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 携带String的页面跳转(TAG)
     *
     * @param clz   跳转的activity
     * @param tag   标识
     * @param value 值
     */
    fun startActivity(clz: Class<*>?, tag: String?, value: String?) {
        val intent = Intent()
        intent.setClass(this, clz!!)
        if (!TextUtils.isEmpty(value)) {
            intent.putExtra(tag, value)
        }
        startActivity(intent)
    }

    protected fun dataResult(num: Int, noData: TextView?) {
        if (noData == null) return
        if (num == 0) {
            noData.visibility = View.VISIBLE
        } else {
            noData.visibility = View.GONE
        }
    }

    fun startActivityForResult(clz: Class<*>?, bundle: Bundle?, requestCode: Int) {
        val intent = Intent()
        intent.setClass(this, clz!!)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    fun startActivityForResult(clz: Class<*>?, requestCode: Int) {
        val intent = Intent()
        intent.setClass(this, clz!!)
        startActivityForResult(intent, requestCode)
    }


    override fun onNetChange(netMobile: Int) {
        this.netMobile = netMobile
        isNetConnect
    }

    /**
     * 网络类型
     */
    private var netMobile = 0

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    private val isNetConnect: Boolean
        get() {
            if (netMobile == NetWorkUtils.NETWORK_WIFI) {
                return true
            } else if (netMobile == NetWorkUtils.NETWORK_MOBILE) {
                return true
            } else if (netMobile == NetWorkUtils.NETWORK_NONE) {
                return false
            }
            return false
        }
}