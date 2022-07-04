package com.view.mvc.app;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.view.mvc.okgo.LoggingInterceptor;
import com.view.mvc.okgo.SSLSocketClient;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * @author :creat by Xia燚
 * 时间：2022/7/4
 * 邮箱：XiahaotianV@163.com
 **/

public class App extends Application {

    public static final long OKGO_MILLISECONDS = 30000;

    @Override
    public void onCreate() {
        super.onCreate();
        initOkGo();
    }



    private void initOkGo() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        LoggingInterceptor loggingInterceptor = new LoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);
        //超时时间设置，默认60秒
        builder.readTimeout(OKGO_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OKGO_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OKGO_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        //忽略证书
        builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory());
        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier());

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);
    }
}
