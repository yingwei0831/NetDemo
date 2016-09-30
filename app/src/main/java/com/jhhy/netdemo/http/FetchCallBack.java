package com.jhhy.netdemo.http;

import com.google.gson.Gson;
import com.jhhy.netdemo.models.FetchError;
import com.jhhy.netdemo.models.FetchResponseModel;
import com.jhhy.netdemo.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

/**
 * Created by zhangguang on 16/9/30.
 */
public class FetchCallBack implements Callback.CommonCallback<String> {

    protected FetchResponse response;

    public FetchCallBack(FetchResponse response){
        this.response = response;
    }

    @Override
    public void onSuccess(String result) {

        try {
            JSONObject resultObj = new JSONObject(result);
            String bodyStr = resultObj.getString("body");
            String headStr = resultObj.getString("head");
            FetchResponseModel model =  new FetchResponseModel(); //new Gson().fromJson(result,FetchResponseModel.class);
            model.body = bodyStr;
            model.head = new Gson().fromJson(headStr,FetchResponseModel.HeadModel.class);
            this.response.onCompletion(model);
            //handler.sendMessage(new Message());
            LogUtil.e("xxxxx","onSuccess =" + result + ", type = " + model.body.getClass() + "toString = " + model.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        HttpException httpEx = (HttpException) ex;
        String responseMsg = httpEx.getMessage();
        String errorResult = httpEx.getResult();
        FetchError error = new FetchError();
        error.errroCode = -1;
        error.msg = responseMsg;
        error.info = errorResult;
        this.response.onError(error);

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
