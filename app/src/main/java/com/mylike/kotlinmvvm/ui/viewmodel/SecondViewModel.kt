package com.mylike.kotlinmvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mylike.kotlinmvvm.common.base.BaseBack
import com.mylike.kotlinmvvm.common.base.BasePageEntity
import com.mylike.kotlinmvvm.common.base.BaseViewModel
import com.mylike.kotlinmvvm.ui.bean.MessageEntity
import com.mylike.kotlinmvvm.ui.model.MainModel

/**
 * Created by ydh on 2022/7/14
 *
 */
class SecondViewModel : BaseViewModel() {
    val liveData = MutableLiveData<List<MessageEntity>>()
    private var mModel = MainModel()
    fun getMessageList(map: Map<String, Any>) {
        mModel.getMessageList(map, object : BaseBack<BasePageEntity<MessageEntity>>() {
            override fun onSuccess(t: BasePageEntity<MessageEntity>) {
                liveData.postValue(t.list)
            }

            override fun onFailed(code: String?, msg: String?) {

            }

        })
    }
}