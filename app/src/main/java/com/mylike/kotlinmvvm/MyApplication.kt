package com.mylike.kotlinmvvm

import android.app.Application
import android.content.Context

/**
 * Created by ydh on 2022/7/5
 *
 */
class MyApplication : Application() {
    companion object {
        const val IS_DEBUG = true
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}