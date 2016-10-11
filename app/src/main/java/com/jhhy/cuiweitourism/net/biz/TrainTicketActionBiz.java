package com.jhhy.cuiweitourism.net.biz;

import android.content.Context;
import android.os.Handler;

import com.jhhy.cuiweitourism.net.models.FetchModel.TrainStationFetch;
import com.jhhy.cuiweitourism.net.models.FetchModel.TrainStopsFetch;
import com.jhhy.cuiweitourism.net.models.FetchModel.TrainTicketFetch;
import com.jhhy.cuiweitourism.net.models.FetchModel.TrainTicketOrderFetch;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchError;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.GenericResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.TrainStationInfo;
import com.jhhy.cuiweitourism.net.models.ResponseModel.TrainStopsInfo;
import com.jhhy.cuiweitourism.net.netcallback.BizGenericCallback;
import com.jhhy.cuiweitourism.net.netcallback.FetchGenericCallback;
import com.jhhy.cuiweitourism.net.netcallback.FetchGenericResponse;
import com.jhhy.cuiweitourism.net.netcallback.FetchResponse;
import com.jhhy.cuiweitourism.net.netcallback.HttpUtils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by birney on 2016-10-10.
 */
public class TrainTicketActionBiz extends BasicActionBiz {

    public TrainTicketActionBiz(Context context, Handler handler) {
        super(context, handler);
    }

    public TrainTicketActionBiz(){

    }

    /**
     *  火车票
     */

    public void trainTicketInfo(TrainTicketFetch fetch, BizGenericCallback<ArrayList<ArrayList<String>>> callback) {
        fetch.code = "Train_index";

        FetchGenericResponse<ArrayList<ArrayList<String>>> fetchResponse = new FetchGenericResponse<ArrayList<ArrayList<String>>>(callback) {
            @Override
            public void onCompletion(FetchResponseModel response) {
                ArrayList<ArrayList<String>> array = parseJsonTotwoLevelArray(response);
                GenericResponseModel model = new GenericResponseModel<>(response.head,array);
                this.bizCallback.onCompletion(model);
            }

            @Override
            public void onError(FetchError error) {
                this.bizCallback.onError(error);
            }
        };

        HttpUtils.executeXutils(fetch, new FetchGenericCallback<>(fetchResponse));
    }


    /**
     *  火车站
     */

    public void trainStationInfo(TrainStationFetch fetch, BizGenericCallback<TrainStationInfo> callback){
        fetch.code = "Train_station";
        FetchGenericResponse<TrainStationInfo> fetchResponse = new FetchGenericResponse<TrainStationInfo>(callback) {
            @Override
            public void onCompletion(FetchResponseModel response) {
                ArrayList<ArrayList<String>> array = parseJsonTotwoLevelArray(response);
                TrainStationInfo stationInfo = new TrainStationInfo(array);
                GenericResponseModel<TrainStationInfo> model = new GenericResponseModel<>(response.head,stationInfo);
                this.bizCallback.onCompletion(model);
            }

            @Override
            public void onError(FetchError error) {
                this.onError(error);
            }
        };

        HttpUtils.executeXutils(fetch,new FetchGenericCallback<>(fetchResponse));
    }

    /**
     *  火车途经站
     */
    public void trainViaStations(TrainStopsFetch fetch, BizGenericCallback<TrainStopsInfo> callback){
        fetch.code = "Train_subwaystation";

        FetchGenericResponse<TrainStopsInfo> fetchResponse = new FetchGenericResponse<TrainStopsInfo>(callback) {
            @Override
            public void onCompletion(FetchResponseModel response) {
                ArrayList<TrainStopsInfo.Stop>  array = parseJsonToObjectArray(response,TrainStopsInfo.Stop.class);
                TrainStopsInfo info = new TrainStopsInfo(array);
                GenericResponseModel<TrainStopsInfo> returnModel = new GenericResponseModel<>(response.head,info);
                this.bizCallback.onCompletion(returnModel);
            }

            @Override
            public void onError(FetchError error) {
                this.bizCallback.onError(error);
            }
        };

        HttpUtils.executeXutils(fetch, new FetchGenericCallback<>(fetchResponse));
    }

    /**
     *  火车票订单提交
     */

    public void trainTicketOrderSubmit(TrainTicketOrderFetch fetch, BizGenericCallback<ArrayList<Object>> callback){
        fetch.code = "Order_trainorder";
        FetchGenericResponse<ArrayList<Object>> fetchResponse = new FetchGenericResponse<ArrayList<Object>>(callback) {
            @Override
            public void onCompletion(FetchResponseModel response) {
                ArrayList<Object> array = parseJsonToObjectArray(response,Object.class);
                GenericResponseModel<ArrayList<Object>> returnModel = new GenericResponseModel<ArrayList<Object>>(response.head,array);
                this.bizCallback.onCompletion(returnModel);
            }

            @Override
            public void onError(FetchError error) {
                this.bizCallback.onError(error);
            }
        };
        HttpUtils.executeXutils(fetch, new FetchGenericCallback<>(fetchResponse));
    }

    /**
     *  火车票订单查询
     */

    public void trainTicketOrderQuery(){

    }

    /**
     *  火车票退票
     */

    public void trainTicketCancel(){

    }

}
