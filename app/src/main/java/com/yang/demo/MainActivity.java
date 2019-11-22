package com.yang.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yang.demo.databinding.ActivityMainBinding;
import com.yang.demo.model.LoginInfo;
import com.yang.demo.vm.LoginViewModel;
import com.yang.network.model.BaseResponse;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public static final String TAG = MainActivity.class.getSimpleName();
    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mLoginViewModel.postLogin().observe(this, new Observer<BaseResponse<LoginInfo>>() {
            @Override
            public void onChanged(BaseResponse<LoginInfo> loginInfoBaseResponse) {
                binding.setLoginInfo(loginInfoBaseResponse.getData());
            }
        });
        binding.setListener(mOnClickListener);
//        binding.btnRequest.setText("跳转轮循网络测试");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        binding.btnRequest.setText("");
//        LoginRequest.getInstance().postLogin().observe(this, new Observer<BaseResponse<LoginInfo>>() {
//            @Override
//            public void onChanged(BaseResponse<LoginInfo> loginInfoBaseResponse) {
//                MLog.e(TAG, "---livedata=====success=========");
//                LoginInfo loginInfo = loginInfoBaseResponse.getData();
//                if (loginInfo != null) {
//                    binding.btnRequest.setText("跳转轮循网络测试" + "\n网络返回的登录名：" + loginInfoBaseResponse.getData().getStudentName());
//                } else {
//                    binding.btnRequest.setText("跳转轮循网络测试" + "\n网络返回的错误信息" + loginInfoBaseResponse.getMessage());
//                }
//            }
//        });
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_request) {
                binding.btnRequest.setText("");
                startActivity(new Intent(MainActivity.this, HttpTestActivity.class));
            }
        }
    };


}
