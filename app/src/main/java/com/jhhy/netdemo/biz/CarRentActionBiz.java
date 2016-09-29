package com.jhhy.netdemo.biz;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.jhhy.netdemo.http.HttpUtils;
import com.jhhy.netdemo.http.ResponseResult;
import com.jhhy.netdemo.models.CarRentFetchModel;
import com.jhhy.netdemo.models.CarRentNextModel;
import com.jhhy.netdemo.models.FetchResponseModel;
import com.jhhy.netdemo.models.Invoice;
import com.jhhy.netdemo.models.Order;
import com.jhhy.netdemo.models.UserContacts;
import com.jhhy.netdemo.utils.Consts;
import com.jhhy.netdemo.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangguang on 16/9/29.
 */
public class CarRentActionBiz extends  BasicActionBiz{
    public CarRentActionBiz(Context context, Handler handler) {
        super(context, handler);
    }


    /**
     *  租车
     */
    public void fetchCarRentalServiceDetail(){
        CarRentFetchModel fetchRequest = new CarRentFetchModel();
        fetchRequest.setBizCode("Car_index");
        LogUtil.e("CarRentActionBiz",fetchRequest.toBizJsonString());
        HttpUtils.executeXutils(fetchRequest,detailCallback);
    }


    /**
     *  租车下一步
     *  @param model   参数，参照文档
     */
    public void carRentNextApi(CarRentNextModel model){
        model.code = "Car_nexts";
        HttpUtils.executeXutils(model,detailCallback);
    }

    private Callback.CommonCallback<String> detailCallback = new Callback.CommonCallback<String>() {

        @Override
        public void onSuccess(String result) {
            try {
                JSONObject resultObj = new JSONObject(result);
                String bodyStr = resultObj.getString("body");
                String headStr = resultObj.getString("head");
                FetchResponseModel model =  new FetchResponseModel(); //new Gson().fromJson(result,FetchResponseModel.class);
                model.body = bodyStr;
                model.head = new Gson().fromJson(headStr,FetchResponseModel.HeadModel.class);

                handler.sendMessage(new Message());
                LogUtil.e("xxxxx","onSuccess =" + result + ", type = " + model.body.getClass() + "toString = " + model.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onError(Throwable ex, boolean isOnCallback) {
            handler.sendEmptyMessage(-1);
            HttpException httpEx = (HttpException) ex;
            String responseMsg = httpEx.getMessage();
            String errorResult = httpEx.getResult();
            LogUtil.e("xxxxx","onError = " + responseMsg + " ---> " + errorResult);
        }

        @Override
        public void onCancelled(CancelledException cex) {

        }

        @Override
        public void onFinished() {
            handler.sendEmptyMessage(0);
        }
    };

}
