package com.jhhy.netdemo.biz;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.jhhy.netdemo.http.FetchCallBack;
import com.jhhy.netdemo.http.FetchResponse;
import com.jhhy.netdemo.http.HttpUtils;
import com.jhhy.netdemo.http.ResponseResult;
import com.jhhy.netdemo.models.BasicResponseModel;
import com.jhhy.netdemo.models.CarRentDetail;
import com.jhhy.netdemo.models.CarRentFetchModel;
import com.jhhy.netdemo.models.CarRentNextModel;
import com.jhhy.netdemo.models.FetchError;
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
import java.util.Iterator;
import java.util.List;


/**
 * Created by zhangguang on 16/9/29.
 */
public class CarRentActionBiz extends  BasicActionBiz{
    public CarRentActionBiz(Context context, Handler handler) {
        super(context, handler);
    }


    public interface  BizCallBack{
        void onError(FetchError error);
        void onCompletion(BasicResponseModel model);
    }

    public  BizCallBack bizCallBack;
    /**
     *  租车
     */
    public void fetchCarRentalServiceDetail(BizCallBack callBack){
        this.bizCallBack = callBack;
        CarRentFetchModel fetchRequest = new CarRentFetchModel();
        fetchRequest.setBizCode("Car_index");
        LogUtil.e("CarRentActionBiz",fetchRequest.toBizJsonString());
        final FetchResponse fetchResponse = new FetchResponse() {
            @Override
            public void onCompletion(FetchResponseModel response) {
                BasicResponseModel returnModel = new BasicResponseModel();
                returnModel.headModel = response.head;
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(response.body);
                if (element.isJsonObject()) {

                } else if (element.isJsonArray()) {
                    ArrayList<CarRentDetail> array = new ArrayList<CarRentDetail>();
                    JsonArray jsonArray = element.getAsJsonArray();
                    Iterator it = jsonArray.iterator();
                    while (it.hasNext()) {
                        JsonElement e = (JsonElement) it.next();
                        CarRentDetail detail = new Gson().fromJson(e, CarRentDetail.class);
                        array.add(detail);
                    }
                    returnModel.body = array;
                    BizCallBack callBack = (BizCallBack) bizCallBack;
                    callBack.onCompletion(returnModel);
                }
            }

            @Override
            public void onError(FetchError error) {
                bizCallBack.onError(error);
            }
        };
        HttpUtils.executeXutils(fetchRequest, new FetchCallBack(fetchResponse));
    }


    /**
     *  租车下一步
     *  @param model   参数，参照文档
     */
    public void carRentNextApi(CarRentNextModel model){
        model.code = "Car_nexts";
        HttpUtils.executeXutils(model,detailCallback);
    }


}
