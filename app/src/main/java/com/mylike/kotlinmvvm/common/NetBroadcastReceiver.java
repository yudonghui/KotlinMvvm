package com.mylike.kotlinmvvm.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.mylike.kotlinmvvm.utils.NetWorkUtils;


/**
 * Created by cheng on 2016/11/28.
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    public NetWorkCallBack mCallBack;

    public NetBroadcastReceiver(NetWorkCallBack mCallBack) {
        this.mCallBack = mCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetWorkUtils.getNetWorkState(context);
            // 接口回调传过去状态的类型
            mCallBack.onNetChange(netWorkState);
        }
    }


    // 自定义接口
    public interface NetWorkCallBack {
         void onNetChange(int netMobile);
    }
}
