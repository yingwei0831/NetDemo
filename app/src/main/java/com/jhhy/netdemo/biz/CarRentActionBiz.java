package com.jhhy.netdemo.biz;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jhhy.netdemo.http.BizCallback;
import com.jhhy.netdemo.http.FetchCallBack;
import com.jhhy.netdemo.http.FetchResponse;
import com.jhhy.netdemo.http.HttpUtils;
import com.jhhy.netdemo.models.ResponseModel.BasicResponseModel;
import com.jhhy.netdemo.models.ResponseModel.CarNextResponse;
import com.jhhy.netdemo.models.ResponseModel.CarRentDetail;
import com.jhhy.netdemo.models.FetchModel.CarRentFetchModel;
import com.jhhy.netdemo.models.FetchModel.CarRentNextModel;
import com.jhhy.netdemo.models.ResponseModel.FetchError;
import com.jhhy.netdemo.models.ResponseModel.FetchResponseModel;
import com.jhhy.netdemo.utils.LogUtil;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by zhangguang on 16/9/29.
 */
public class CarRentActionBiz extends  BasicActionBiz{
    public CarRentActionBiz(Context context, Handler handler) {
        super(context, handler);
    }

    private static final String BIZTAG = "CarRentActionBiz";

    public JsonElement parseJsonBody(FetchResponseModel model){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(model.body);
        return element;
    }

    /**
     *  租车
     */
    public void fetchCarRentalServiceDetail(BizCallback callBack){
        CarRentFetchModel fetchRequest = new CarRentFetchModel();
        fetchRequest.setBizCode("Car_index");
        LogUtil.e(BIZTAG,fetchRequest.toBizJsonString());

        final FetchResponse fetchResponse = new FetchResponse(callBack) {
            @Override
            public void onCompletion(FetchResponseModel response) {
                BasicResponseModel returnModel = new BasicResponseModel();
                returnModel.headModel = response.head;
                JsonElement element = parseJsonBody(response);
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
                    this.bizCallback.onCompletion(returnModel);
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
    public void carRentNextApi(CarRentNextModel model,BizCallback callBack){
        model.code = "Car_nexts";
        FetchResponse fetchResponse = new FetchResponse(callBack) {
            @Override
            public void onCompletion(FetchResponseModel response) {
                BasicResponseModel returnModel = new BasicResponseModel();
                JsonElement element = parseJsonBody(response);
                if (element.isJsonObject()){
                    CarNextResponse carNext = new Gson().fromJson(element,CarNextResponse.class);
                    returnModel.headModel = response.head;
                    returnModel.body = carNext;
                    this.bizCallback.onCompletion(returnModel);
                }
                else{
                    // exception
                }
            }
            @Override
            public void onError(FetchError error) {
                bizCallBack.onError(error);
            }
        };
        HttpUtils.executeXutils(model,new FetchCallBack(fetchResponse));
    }

}
