package com.mylike.kotlinmvvm.ui.viewmodel

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mylike.kotlinmvvm.common.base.BaseBack
import com.mylike.kotlinmvvm.common.base.BasePageEntity
import com.mylike.kotlinmvvm.common.base.BaseViewModel
import com.mylike.kotlinmvvm.databinding.ActivityMainBinding
import com.mylike.kotlinmvvm.ui.bean.InfoEntity
import com.mylike.kotlinmvvm.ui.bean.MessageEntity
import com.mylike.kotlinmvvm.ui.model.MainModel
import com.mylike.kotlinmvvm.ui.view.MainActivity

/**
 * Created by ydh on 2022/7/5
 *
 */
class MainViewModel : BaseViewModel() {
    val liveData = MutableLiveData<List<MessageEntity>>()
    val infoEntity = MutableLiveData<InfoEntity>()
    private var mModel = MainModel()
    fun getUserInfo(map: Map<String, Any>, dataBinding: ActivityMainBinding) {
        mModel?.getUserInfo(map, object : BaseBack<InfoEntity>() {
            override fun onSuccess(t: InfoEntity) {
               // dataBinding.infoEntity = t
                infoEntity.postValue(t)

            }

            override fun onFailed(code: String?, msg: String?) {

            }

        })
    }

    fun getMessageList(map: Map<String, Any>, refresh: SwipeRefreshLayout) {
        mModel.getMessageList(map, object : BaseBack<BasePageEntity<MessageEntity>>() {
            override fun onSuccess(t: BasePageEntity<MessageEntity>) {
                liveData.postValue(t.list)
                refresh.isRefreshing = false
            }

            override fun onFailed(code: String?, msg: String?) {

            }

        })
    }
}