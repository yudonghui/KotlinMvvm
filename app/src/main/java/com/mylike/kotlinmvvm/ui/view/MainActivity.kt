package com.mylike.kotlinmvvm.ui.view

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.TestKtActivity
import com.mylike.kotlinmvvm.UserInfoBy
import com.mylike.kotlinmvvm.common.GridDecoration
import com.mylike.kotlinmvvm.common.RvDividerDecoration
import com.mylike.kotlinmvvm.common.SPUtils
import com.mylike.kotlinmvvm.common.SpaceItemDecoration
import com.mylike.kotlinmvvm.common.base.BaseMvvmActivity
import com.mylike.kotlinmvvm.common.expand.init
import com.mylike.kotlinmvvm.databinding.ActivityMainBinding
import com.mylike.kotlinmvvm.haohao.ListActivity
import com.mylike.kotlinmvvm.haohao.MainActivity
import com.mylike.kotlinmvvm.ui.adapter.MessageAdapter
import com.mylike.kotlinmvvm.ui.bean.InfoEntity
import com.mylike.kotlinmvvm.ui.viewmodel.MainViewModel
import com.mylike.kotlinmvvm.utils.CommonUtil
import com.mylike.kotlinmvvm.utils.LogUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.random.Random

/**
 * https://blog.csdn.net/qq_32400821/article/details/79836788
 * https://cloud.tencent.com/developer/article/1999750
 * https://blog.csdn.net/klylove/article/details/122039737
 * https://blog.csdn.net/songzi1228/article/details/116481729
 */
class MainActivity : BaseMvvmActivity() {
    public lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel
    private lateinit var name: String
    private lateinit var mMessageAdapter: MessageAdapter

    override fun initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        tv_loginout.setOnClickListener {
            SPUtils.clearCache(SPUtils.FILE_USER)
            startActivity(LoginActivity::class.java)
            finish()
        }
        tv_skip.setOnClickListener {
            startActivity(ListActivity::class.java)
        }
        LogUtils.e(UserInfoBy().yud("重恩") { i: Int, i1: Int -> i + i1 })
        initAdapter()
        initListener()
        mBinding.imgRes = R.mipmap.default_header
        mBinding.imgUrl =
            "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.jj20.com%2Fup%2Fallimg%2Ftp02%2F1Z91915023J933-0-lp.jpg&refer=http%3A%2F%2Fimg.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1660380505&t=a4e98dd4f0fb4550db2ec3fe80a1ad6d"

    }


    private fun initListener() {
        refresh_layout.setColorSchemeColors(
            ContextCompat.getColor(this, R.color.color_theme),
            ContextCompat.getColor(this, R.color.color_black),
            ContextCompat.getColor(this, R.color.color_red)
        )
        refresh_layout.setOnRefreshListener {
            initData(Random.nextInt(10))
        }
    }

    private fun initData(pageSize: Int) {
        var map = mutableMapOf<String, Any>()
        map["pageNumber"] = 1
        map["pageSize"] = pageSize
        mViewModel.getMessageList(map, refresh_layout)
    }

    private fun initAdapter() {
        mMessageAdapter = MessageAdapter()
        var layoutManager =
            LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        /*  rv_message.init(
              layoutManager,
              mMessageAdapter,
              SpaceItemDecoration(CommonUtil.dp2px(10.0), SpaceItemDecoration.LINEARLAYOUT)
          )*/
        /*rv_message.init(
            layoutManager,
            mMessageAdapter,
            RvDividerDecoration(this, RecyclerView.HORIZONTAL, 1.0, R.color.color_divide_line)
        )*/
        rv_message.init(
            GridLayoutManager(this, 5),
            mMessageAdapter,
            GridDecoration(this, R.drawable.divider_red)
        )

        mViewModel.liveData.observe(this, {
            if (it.isNotEmpty()) {
                mMessageAdapter.setList(it)
            }
        })
        mViewModel.infoEntity.observe(this) {
            mBinding.infoEntity = it
        }
    }

    fun getUserInfo(view: View) {
        var map = mutableMapOf<String, Any>()
        map["customerId"] = "4e8f3540-0b8f-4b0a-a29b-708447f8ba17"
        map["deptId"] = ""
        map["tenantId"] = "851010"
        mViewModel.getUserInfo(map, mBinding)
    }

    fun goSecond(view: View) {
        startActivity(SecondActivity::class.java)
    }

    fun importStatic(view: View) {
        mViewModel.infoEntity.value?.a = 10
        mViewModel.infoEntity.value?.b = 20
        mViewModel.infoEntity.postValue(mViewModel.infoEntity.value)
        //mBinding.executePendingBindings()
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun addQuick(view: View) {
        var shortcutManager = getSystemService(ShortcutManager::class.java)
        var shortcut = ShortcutInfo.Builder(this, "test_add")
            .setShortLabel("测试添加")
            .setIcon(Icon.createWithResource(mContext, R.mipmap.default_header))
            .setIntent(Intent(Intent.ACTION_MAIN, null, mContext, ListActivity::class.java))
            .build()
        shortcutManager.dynamicShortcuts = listOf(shortcut)
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun updateQuick(view: View) {
        var shortcutManager = getSystemService(ShortcutManager::class.java)
        var shortcut = ShortcutInfo.Builder(this, "test_add")
            .setShortLabel("更新成功")
            .setLongLabel("长更新成功")
            .setIcon(Icon.createWithResource(mContext, R.mipmap.default_header))
            .setIntent(
                Intent(
                    Intent.ACTION_MAIN,
                    null,
                    mContext,
                    com.mylike.kotlinmvvm.ui.view.MainActivity::class.java
                )
            )
            .build()
        shortcutManager.updateShortcuts(listOf(shortcut))
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun removeQuick(view: View) {
        var shortcutManager = getSystemService(ShortcutManager::class.java)
        shortcutManager.removeDynamicShortcuts(listOf("test_add"));//唯一的id标识
    }
}

