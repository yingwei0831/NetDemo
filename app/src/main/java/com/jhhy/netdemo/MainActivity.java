package com.jhhy.netdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jhhy.netdemo.biz.CarRentActionBiz;
import com.jhhy.netdemo.biz.OrderActionBiz;
import com.jhhy.netdemo.http.BizCallback;
import com.jhhy.netdemo.models.FetchModel.CarModel;
import com.jhhy.netdemo.models.FetchModel.CarPriceEstimate;
import com.jhhy.netdemo.models.FetchModel.CarRentCity;
import com.jhhy.netdemo.models.FetchModel.CarRentOrder;
import com.jhhy.netdemo.models.FetchModel.CarRentPickLocation1;
import com.jhhy.netdemo.models.FetchModel.CarRentPickLocation2;
import com.jhhy.netdemo.models.ResponseModel.BasicResponseModel;
import com.jhhy.netdemo.models.FetchModel.CarRentNextModel;
import com.jhhy.netdemo.models.ResponseModel.CarNextResponse;
import com.jhhy.netdemo.models.ResponseModel.CarRentDetail;
import com.jhhy.netdemo.models.ResponseModel.CarRentOrderResponse;
import com.jhhy.netdemo.models.ResponseModel.FetchError;
import com.jhhy.netdemo.models.TestStudent;
import com.jhhy.netdemo.models.Order;
import com.jhhy.netdemo.utils.Consts;
import com.jhhy.netdemo.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public  static  String TAG = "XXXX";
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Consts.MESSAGE_ORDER_DETAIL:
                    if (msg.arg1 == 1){
                        Order order = (Order) msg.obj;
//                        LogUtil.e("info", "info = "+order.toString());
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        OrderActionBiz biz = new OrderActionBiz(getApplicationContext(), handler);
//        biz.getOrderDetail("01585988836818");

        CarRentActionBiz carBiz = new CarRentActionBiz(getApplicationContext(),handler);
        carBiz.fetchCarRentalServiceDetail(new BizCallback() {
            @Override
            public void onCompletion(BasicResponseModel model) {
                ArrayList<CarRentDetail> array = (ArrayList<CarRentDetail>) model.body;
                LogUtil.e(TAG, "fetchCarRentalServiceDetail = " + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " fetchCarRentalServiceDetail :" + error.toString());
            }
        });


        CarRentNextModel model = new CarRentNextModel("5","2","北京市昌平区史各庄","辽宁省凌源市火车站");
        carBiz.carRentNextApi(model, new BizCallback(){
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentNextApi :" + error.toString());
            }
            @Override
            public void onCompletion(BasicResponseModel model) {
                CarNextResponse next = (CarNextResponse) model.body;
                LogUtil.e(TAG, "carRentNextApi = " + next.toString());
            }
        });


        CarRentOrder carOrder = new CarRentOrder("11","7","2","北京市昌平区史各庄",
                "辽宁省凌源市火车站","2016-08-30","2500","张三","15210656332","金龙客车(55座)");
        carBiz.carRentSubmitOrder(carOrder, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " CarRentSubmitOrder :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                CarRentOrderResponse response =(CarRentOrderResponse)model.body;
                LogUtil.e(TAG, "CarRentSubmitOrder = " + response.toString());
            }
        });


        CarRentCity carCity = new CarRentCity("");
        carBiz.carRentGetCitys(carCity, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " CarRentGetCitys :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                List<List<String>> result =(List<List<String>>)model.body;
                LogUtil.e(TAG, "CarRentGetCitys = " + result.toString());
            }
        });


        CarModel carModel = new CarModel("1","201");
        carBiz.carRentGetCityCarModel(carModel, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentGetCityCarModel :" + error.toString());
            }
            @Override
            public void onCompletion(BasicResponseModel model) {

                List<List<String>> result =(List<List<String>>)model.body;
                LogUtil.e(TAG, "carRentGetCityCarModel = " + result.toString());
            }
        });

        CarRentPickLocation1 location1 = new CarRentPickLocation1("北京市");
        carBiz.carRentGetPickupLocationForAirport(location1, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentGetPickupLocationForAirport :" + error.toString());
            }
            @Override
            public void onCompletion(BasicResponseModel model) {
                ArrayList<ArrayList<String>> result =(ArrayList<ArrayList<String>>)model.body;
                LogUtil.e(TAG, "carRentGetPickupLocationForAirport = " + result.toString());
            }
        });


        CarRentPickLocation2 location2 = new CarRentPickLocation2("北京市","浙江大厦");
        carBiz.carRentGetPickupLocationForOfficeBuilding(location2, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentGetPickupLocationForOfficeBuilding :" + error.toString());
            }
            @Override
            public void onCompletion(BasicResponseModel model) {
                ArrayList<ArrayList<String>> result =(ArrayList<ArrayList<String>>)model.body;
                LogUtil.e(TAG, "carRentGetPickupLocationForOfficeBuilding = " + result.toString());
            }
        });

        //{"fromlat":"40.081115580237","fromlng":"116.58797959531",
        // "arrivelat":"39.96956","arrivelng":"116.40029","ruletype":"201","cityid":"1"
        CarPriceEstimate priceEstimate = new CarPriceEstimate("40.081115580237","116.58797959531","39.96956","116.40029","201","1");
        carBiz.carRentPriceEstimate(priceEstimate, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " carRentPriceEstimate :" + error.toString());
            }
            @Override
            public void onCompletion(BasicResponseModel model) {
                ArrayList<ArrayList<String>> result =(ArrayList<ArrayList<String>>)model.body;
                LogUtil.e(TAG, "carRentPriceEstimate = " + result.toString());
            }
        });
    }
}
