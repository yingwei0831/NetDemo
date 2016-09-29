package com.jhhy.netdemo.biz;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.jhhy.netdemo.http.HttpUtils;
import com.jhhy.netdemo.http.ResponseResult;
import com.jhhy.netdemo.models.CarRentFetchModel;
import com.jhhy.netdemo.models.Invoice;
import com.jhhy.netdemo.models.Order;
import com.jhhy.netdemo.models.UserContacts;
import com.jhhy.netdemo.utils.Consts;
import com.jhhy.netdemo.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangguang on 16/9/29.
 */
public class CarRentActionBiz extends  BasicActionBiz{
    public CarRentActionBiz(Context context, Handler handler) {
        super(context, handler);
    }


    public void fetchCarRentalServiceDetail(){
        CarRentFetchModel fetchRequest = new CarRentFetchModel();
        fetchRequest.setBizCode("Car_index");
        LogUtil.e("CarRentActionBiz",fetchRequest.toBizJsonString());
        HttpUtils.executeXutils(fetchRequest,detailCallback);
    }



    private ResponseResult detailCallback = new ResponseResult() {
        @Override
        public void responseSuccess(String result) {
          //  LogUtil.e(TAG, "返回数据：" + result);
            Message msg = new Message();

        }
    };

}
