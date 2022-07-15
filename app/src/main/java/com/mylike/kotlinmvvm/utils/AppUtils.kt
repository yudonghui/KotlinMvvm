package com.mylike.kotlinmvvm.utils

import com.mylike.kotlinmvvm.MyApplication

/**
 * Created by ydh on 2022/7/5
 *
 */
class AppUtils {
    companion object {
        fun getVersionCode(): Int {
            return try {
                MyApplication.context.packageManager.getPackageInfo(
                    MyApplication.context.packageName,
                    0
                ).versionCode
            } catch (e: Exception) {
                0
            }
        }

        fun getVersionName(): String {
            return try {
                MyApplication.context.packageManager.getPackageInfo(
                    MyApplication.context.packageName,
                    0
                ).versionName
            } catch (e: Exception) {
                ""
            }
        }
    }
}