package com.mylike.kotlinmvvm.common

import android.content.Context.MODE_PRIVATE
import android.widget.Switch
import com.mylike.kotlinmvvm.MyApplication
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by ydh on 2022/7/5
 *
 */
class SPUtils {
    companion object {
        const val FILE_USER = "cache_user"
        const val TOKEN = "token"
        fun setCache(fileName: String, spName: String, spValue: String) {
            var sp =
                MyApplication.context.getSharedPreferences(fileName, MODE_PRIVATE)
            var edit = sp.edit()
            edit.putString(spName, spValue)
            edit.commit()
        }

        fun setCache(fileName: String, map: HashMap<String, Any?>) {
            var sp =
                MyApplication.context.getSharedPreferences(fileName, MODE_PRIVATE)
            var edit = sp.edit()
            for ((k, v) in map) {
                when (v) {
                    is String -> edit.putString(k, v)
                    is Int -> edit.putInt(k, v)
                    is Boolean -> edit.putBoolean(k, v)
                }
            }
            edit.commit()
        }

        fun getCacheStr(fileName: String, spName: String): String? {
            var sp =
                MyApplication.context.getSharedPreferences(fileName, MODE_PRIVATE)
            return sp.getString(spName, "")
        }

        fun getCacheInt(fileName: String, spName: String): Int? {
            var sp =
                MyApplication.context.getSharedPreferences(fileName, MODE_PRIVATE)
            return sp.getInt(spName, 0)
        }

        fun getCacheBoolean(fileName: String, spName: String): Boolean? {
            var sp =
                MyApplication.context.getSharedPreferences(fileName, MODE_PRIVATE)
            return sp.getBoolean(spName, false)
        }

        fun clearCache(fileName: String) {
            var sp =
                MyApplication.context.getSharedPreferences(fileName, MODE_PRIVATE)
            var edit = sp.edit()
            edit.clear()
            edit.commit()
        }
    }

}