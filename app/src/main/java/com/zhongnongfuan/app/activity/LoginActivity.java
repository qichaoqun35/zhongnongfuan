package com.zhongnongfuan.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.zhongnongfuan.app.R;
import com.zhongnongfuan.app.dialog.ProgressShowDialog;
import com.zhongnongfuan.app.utils.SaveAndGetUser;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author qichaoqun
 * @date 2019/1/19
 */
public class LoginActivity extends Activity {

    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.input_user_name)
    EditText mNameEdit;
    @BindView(R.id.input_user_password)
    EditText mPassEdit;
    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
        mIntent = new Intent();
        loginButtonClick();
    }

    /**
     * 防止登陆按钮重复点击，由此进入主页面
     */
    private void loginButtonClick() {
        RxView.clicks(loginButton).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                       /* ProgressShowDialog showDialog = new ProgressShowDialog(LoginActivity.this);
                        showDialog.show();*/
                        //获取用户输入的值
                        String userName = mNameEdit.getText().toString();
                        String passWord = mPassEdit.getText().toString();
                        Log.i("用户名是多少：：", "loginClick: " + userName);
                        Log.i("用户密码是多少：：；", "loginClick: " + passWord);
                        /*if("".equals(userName) || "".equals(passWord)){
                            //提示用户名和密码不能为空
                            Toast.makeText(this,"请输入有效的用户名和密码",Toast.LENGTH_SHORT).show();
                        }else{
                            //请求网络，检查用户名和密码是否为空
                            //暂时认为密码和用户名都是真的，因此可以将用户名和密码保存起来，并且跳转到主页面
                            SaveAndGetUser saveAndGetUser = new SaveAndGetUser(this);
                            saveAndGetUser.setUser(userName,passWord);
                            //跳转到主页面
                            Intent intent = new Intent(this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }*/
                        mIntent.setClass(LoginActivity.this, MainActivity.class);
                        startActivity(mIntent);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
