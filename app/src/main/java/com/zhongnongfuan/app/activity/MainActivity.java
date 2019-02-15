package com.zhongnongfuan.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongnongfuan.app.R;
import com.zhongnongfuan.app.adapter.OnRecyclerviewItemClickListener;
import com.zhongnongfuan.app.adapter.RecyclerViewAdapter;
import com.zhongnongfuan.app.bean.Machine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author qichaoqun
 * @create 2019/1/19
 * @Describe
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    public static final int QUIT_TIME = 2000;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.no_content)
    TextView noContent;

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<Machine> mMachines = new ArrayList<>();
    private long mStartTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        loadData();
    }
    /**
     * 从网络获取数据
     */
    private void loadData() {
        mMachines.add(new Machine("第一号机器", "郑州轻工业大学", "进粮"));
        mMachines.add(new Machine("第二号机器", "郑州轻工业大学", "干燥"));
        mMachines.add(new Machine("第三号机器", "郑州轻工业大学", "排粮"));
        mMachines.add(new Machine("第四号机器", "郑州轻工业大学", "排粮"));
        mMachines.add(new Machine("第五号机器", "郑州轻工业大学", "干燥"));
        mMachines.add(new Machine("第六号机器", "郑州轻工业大学", "进粮"));
        mRecyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, mMachines);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        //设置列表的监听事件
        mRecyclerViewAdapter.setOnItemClickListener(new OnRecyclerviewItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Log.i("用户点击的位置是：：；", "onItemClickListener: " + position);
                //假设根据机器的编号来获取相关的信息
                Intent intent = new Intent(MainActivity.this, MachineActivity.class);
                intent.putExtra("machine_name", mMachines.get(position).getMachineName());
                startActivity(intent);
            }
        });
        mProgressBar.setVisibility(View.GONE);
    }

    /**
     * 初始化控件和各种数据
     */
    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //初始化下拉刷新
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //初始化RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        //将用户的所有的机器数设置到RecyclerView上
        if (mMachines != null) {
            mMachines.clear();
        }
        mMachines.add(new Machine("第7号机器", "郑州轻工业大学", "进粮"));
        mMachines.add(new Machine("第8号机器", "郑州轻工业大学", "干燥"));
        mMachines.add(new Machine("第9号机器", "郑州轻工业大学", "排粮"));
        mMachines.add(new Machine("第10号机器", "郑州轻工业大学", "排粮"));
        mMachines.add(new Machine("第11号机器", "郑州轻工业大学", "干燥"));
        mMachines.add(new Machine("第12号机器", "郑州轻工业大学", "进粮"));
        mRecyclerViewAdapter.flushList(mMachines);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            long endTime = System.currentTimeMillis();
            if (endTime - mStartTime > QUIT_TIME) {
                Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                mStartTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
