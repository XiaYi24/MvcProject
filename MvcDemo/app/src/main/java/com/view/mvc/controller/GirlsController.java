package com.view.mvc.controller;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.view.mvc.bean.GirlBean;
import com.view.mvc.http.HttpConstants;
import com.view.mvc.http.IdMessage;
import com.view.mvc.okgo.JsonCallback;
import com.view.mvc.okgo.LzyResponse;

import java.util.List;

/**
 * @author :creat by Xia燚
 * 时间：2022/7/4
 * 邮箱：XiahaotianV@163.com
 **/
public class GirlsController extends BaseController {


    public GirlsController(Context ctx) {
        super(ctx);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdMessage.GIRLS_NETWORK_ACTION:
                getGirlList();
                break;
        }
    }


    /**
     * 获取图片集合
     */
    private void getGirlList() {

        OkGo.<LzyResponse<List<GirlBean>>>get(HttpConstants.GET_GIRLS)
                .tag(this)
                .execute(new JsonCallback<LzyResponse<List<GirlBean>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<GirlBean>>> response) {
                        List<GirlBean> data = response.body().data;
                        mListener.onModeChanged(IdMessage.GIRLS_NETWORK_ACTION_RESULT, data);
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<GirlBean>>> response) {
                        super.onError(response);
                    }
                });



    }
}
