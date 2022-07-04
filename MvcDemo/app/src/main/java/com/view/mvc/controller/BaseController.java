package com.view.mvc.controller;

import android.content.Context;

import com.view.mvc.listenter.IModeChangeListener;

/**
 * @author :creat by Xia燚
 * 时间：2022/7/4
 * 邮箱：XiahaotianV@163.com
 * Controller 基类
 **/
public abstract class BaseController {

    protected Context mContext;
    protected IModeChangeListener mListener;

    public void setIModeChangeListener(IModeChangeListener listener) {
        this.mListener = listener;
    }

    public BaseController(Context ctx) {
        this.mContext = ctx;
    }


    /**
     * 一个页面可能有多个网络请求  action区别网络请求
     * values 请求的数据
     */
    public void sendAsyncMessage(final int action, final Object... values) {

        handleMessage(action, values);

        //TODO 这里可以开启子线程 处理一些耗时操作

        /**
         * new Thread() {
        @Override public void run() {
        handleMessage(action, values);
        }
        }.start();*/
    }


    protected abstract void handleMessage(int action, Object... values);
}
