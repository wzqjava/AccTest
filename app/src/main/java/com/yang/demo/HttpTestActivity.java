package com.yang.demo;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.yang.demo.databinding.ActivityHttpTestBinding;
import com.yang.demo.databinding.ActivityMainBinding;
import com.yang.demo.http.LoginRequest;
import com.yang.demo.model.LoginInfo;
import com.yang.network.model.BaseResponse;
import com.yang.network.utils.MLog;

/**
 * Created by yang on 2019/11/22.
 */
public class HttpTestActivity extends AppCompatActivity {
    private ActivityHttpTestBinding binding;
    public static final String TAG = HttpTestActivity.class.getSimpleName();
    int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_http_test);
        binding.tvRequestContent.setText("已开启轮循网络服务");
        startPolling();
    }

    private void startPolling() {
        LoginRequest.getInstance().postLogin().observe(this, new Observer<BaseResponse<LoginInfo>>() {
            @Override
            public void onChanged(BaseResponse<LoginInfo> loginInfoBaseResponse) {
                MLog.e(TAG, "---livedata=====success=========");
                i++;
                LoginInfo loginInfo = loginInfoBaseResponse.getData();
                if (loginInfo != null) {
                    binding.tvRequestContent.setText("已开启轮循网络服务\n轮循遍数" + "" + i + "\n" + loginInfoBaseResponse.getData().getStudentName());
                } else {
                    binding.tvRequestContent.setText("已开启轮循网络服务\n轮循遍数" + "" + i + "\n" + loginInfoBaseResponse.getMessage());
                }
                App.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startPolling();
                    }
                }, 1000);
            }
        });
    }

}
