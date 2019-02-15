package com.zhongnongfuan.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.zhongnongfuan.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author qichaoqun
 * @date 2019/1/19
 */
public class MachineActivity extends AppCompatActivity {

    @BindView(R.id.machine_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_layout);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String machineName = intent.getStringExtra("machine_name");
        Log.i("用户选择的机器的名字为：：：", "onCreate: "+machineName);

        mToolbar.setTitle("详细状态信息");
        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
