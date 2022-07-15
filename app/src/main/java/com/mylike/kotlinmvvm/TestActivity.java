package com.mylike.kotlinmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mylike.kotlinmvvm.beans.TokenEntity;
import com.mylike.kotlinmvvm.common.base.BaseBack;
import com.mylike.kotlinmvvm.ui.model.LoginModel;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new LoginModel().getLogin(new HashMap<String, Object>(), new BaseBack<TokenEntity>() {

            @Override
            protected void onFailed(@Nullable String code, @Nullable String msg) {

            }

            @Override
            protected void onSuccess(@Nullable TokenEntity tokenEntity) {

            }
        });
    }
}