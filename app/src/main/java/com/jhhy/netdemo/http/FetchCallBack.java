package com.jhhy.netdemo.http;

import com.google.gson.Gson;
import com.jhhy.netdemo.models.ResponseModel.FetchError;
import com.jhhy.netdemo.models.ResponseModel.FetchResponseModel;
import com.jhhy.netdemo.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

import java.net.UnknownHostException;

/**
 * Created by zhangguang on 16/9/30.
 */
public class FetchCallBack implements Callback.CommonCallback<String> {

    protected FetchResponse response;

    private static final String TAG = "FetchCallBack";

    public FetchCallBack(FetchResponse response){
        this.response = response;
    }

    @Override
    public void onSuccess(String result) {
        LogUtil.e(TAG," " + result);
        try {
            JSONObject resultObj = new JSONObject(result);
            String bodyStr = resultObj.getString("body");
            String headStr = resultObj.getString("head");
            FetchResponseModel model =  new FetchResponseModel(); //new Gson().fromJson(result,FetchResponseModel.class);
            model.body = bodyStr;
            model.head = new Gson().fromJson(headStr,FetchResponseModel.HeadModel.class);
            this.response.onCompletion(model);
            //handler.sendMessage(new Message());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        String responseMsg =  ex.getMessage();
        String errorResult = ex.getLocalizedMessage();
        FetchError error = new FetchError();
        error.errroCode = -1;
        error.msg = responseMsg;
        error.info = errorResult;
        this.response.onError(error);
        LogUtil.e(TAG, " " + error.msg + error.info);
//        if (ex instanceof HttpException) {
//            HttpException httpEx = (HttpException) ex;
//            String responseMsg = httpEx.getMessage();
//            String errorResult = httpEx.getResult();
//            FetchError error = new FetchError();
//            error.errroCode = -1;
//            error.msg = responseMsg;
//            error.info = errorResult;
//            this.response.onError(error);
//            LogUtil.e(TAG, " " + error.msg + error.info);
//        }else if (ex instanceof UnknownHostException){
//
//        }
//        LogUtil.e(TAG, " " + ex.toString());
    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
