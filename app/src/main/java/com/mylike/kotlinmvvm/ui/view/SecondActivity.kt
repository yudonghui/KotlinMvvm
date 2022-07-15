package com.mylike.kotlinmvvm.ui.view

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.hardware.TriggerEvent
import android.hardware.TriggerEventListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.common.SpaceItemDecoration
import com.mylike.kotlinmvvm.common.base.BaseMvvmActivity
import com.mylike.kotlinmvvm.common.expand.init
import com.mylike.kotlinmvvm.databinding.ActivitySecondBinding
import com.mylike.kotlinmvvm.ui.adapter.MessageAdapter2
import com.mylike.kotlinmvvm.ui.bean.InfoEntity
import com.mylike.kotlinmvvm.ui.viewmodel.SecondViewModel
import com.mylike.kotlinmvvm.utils.CommonUtil
import com.mylike.kotlinmvvm.utils.LogUtils
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseMvvmActivity() {
    private lateinit var mBinding: ActivitySecondBinding
    private lateinit var mViewModel: SecondViewModel
    private lateinit var mMessageAdapter: MessageAdapter2
    private lateinit var sensorManager: SensorManager
    private lateinit var triggerEventListener: TriggerEventListener
    private lateinit var defaultSensor: Sensor

    override fun initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        mViewModel = ViewModelProvider(this).get(SecondViewModel::class.java)
        mBinding.lifecycleOwner = this
        mBinding.viewModel = mViewModel
        initAdapter()
        initObserver()
        var map = mutableMapOf<String, Any>()
        map["pageNumber"] = 1
        map["pageSize"] = 10
        mViewModel.getMessageList(map)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        defaultSensor = sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION)
        triggerEventListener = object : TriggerEventListener() {
            override fun onTrigger(event: TriggerEvent?) {
                LogUtils.e("进入  onTrigger")
                textview.append("事件：${event.toString()}")
            }

        }
        defaultSensor?.also {
            sensorManager.requestTriggerSensor(triggerEventListener, defaultSensor)
        }

    }

    override fun onPause() {
        super.onPause()
        // Call disable to ensure that the trigger request has been canceled.
        sensorManager.cancelTriggerSensor(triggerEventListener, defaultSensor)
    }

    private fun initObserver() {
        mViewModel.liveData.observe(this) {
            mMessageAdapter.list.addAll(it)
            mMessageAdapter.notifyDataSetChanged()
        }
    }

    private fun initAdapter() {
        mMessageAdapter = MessageAdapter2(ArrayList(), mBinding)
        var layoutManager =
            LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        recycler_view.init(
            layoutManager,
            mMessageAdapter,
            SpaceItemDecoration(CommonUtil.dp2px(10.0), SpaceItemDecoration.LINEARLAYOUT)
        )
    }
}