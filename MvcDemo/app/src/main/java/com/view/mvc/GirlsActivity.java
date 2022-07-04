package com.view.mvc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.view.mvc.adapter.GirlsAdapter;
import com.view.mvc.base.BaseActivity;
import com.view.mvc.bean.GirlBean;
import com.view.mvc.controller.GirlsController;
import com.view.mvc.http.IdMessage;
import com.view.mvc.listenter.IModeChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :creat by Xia燚
 * 时间：2022/7/4
 * 邮箱：XiahaotianV@163.com
 **/
public class GirlsActivity extends BaseActivity implements IModeChangeListener {

    private RecyclerView rcList;
    private GirlsController controller;
    private List<GirlBean> girList;
    private GirlsAdapter adapter;

    //回调
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case IdMessage.GIRLS_NETWORK_ACTION_RESULT:
                    List<GirlBean> data  = (List<GirlBean>) msg.obj;
                    girList.addAll(data);
                    adapter.notifyDataSetChanged();
                    break;

            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_girls;
    }

    @Override
    public void initViews() {
        girList = new ArrayList<>();
        controller = new GirlsController(this);
        controller.setIModeChangeListener(this);
        rcList = findViewById(R.id.rc_list);
        rcList.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new GirlsAdapter(R.layout.item_girl,girList);
        rcList.setAdapter(adapter);
    }

    @Override
    public void initData() {

        controller.sendAsyncMessage(IdMessage.GIRLS_NETWORK_ACTION);
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void onModeChanged(int action, Object... values) {
        handler.obtainMessage(action, values[0]).sendToTarget();
    }
}
