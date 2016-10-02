package com.jhhy.netdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jhhy.netdemo.biz.CarRentActionBiz;
import com.jhhy.netdemo.biz.OrderActionBiz;
import com.jhhy.netdemo.http.BizCallback;
import com.jhhy.netdemo.models.FetchModel.CarRentCity;
import com.jhhy.netdemo.models.FetchModel.CarRentOrder;
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

        OrderActionBiz biz = new OrderActionBiz(getApplicationContext(), handler);
        biz.getOrderDetail("01585988836818");

        CarRentActionBiz carBiz = new CarRentActionBiz(getApplicationContext(),handler);
        carBiz.fetchCarRentalServiceDetail(new BizCallback() {
            @Override
            public void onCompletion(BasicResponseModel model) {
                ArrayList<CarRentDetail> array = (ArrayList<CarRentDetail>) model.body;
                LogUtil.e(TAG, "fetchCarRentalServiceDetail = " + array.toString());
            }

            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " error :" + error.toString());
            }
        });


        CarRentNextModel model = new CarRentNextModel("5","2","北京市昌平区史各庄","辽宁省凌源市火车站");
        carBiz.carRentNextApi(model, new BizCallback(){
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " error :" + error.toString());
            }
            @Override
            public void onCompletion(BasicResponseModel model) {
                CarNextResponse next = (CarNextResponse) model.body;
                LogUtil.e(TAG, "carRentNextApi = " + next.toString());
            }
        });

        //{"memberid":"6","carid":"7","days":"2","startaddress":"北京市昌平区史各庄",
        // "endaddress":"辽宁省凌源市火车站","usetime":"2016-08-30",
        // "price":"2500","linkman":"张三","linktel":"15210656332",
        // "productname":"金龙客车(55座)"}
        CarRentOrder carOrder = new CarRentOrder("6","7","2","北京市昌平区史各庄",
                "辽宁省凌源市火车站","2016-08-30","2500","张三","15210656332","金龙客车(55座)");
        carBiz.CarRentSubmitOrder(carOrder, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " error :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                CarRentOrderResponse response =(CarRentOrderResponse)model.body;
                LogUtil.e(TAG, "CarRentSubmitOrder = " + response.toString());
            }
        });


        CarRentCity carCity = new CarRentCity("");
        carBiz.CarRentGetCitys(carCity, new BizCallback() {
            @Override
            public void onError(FetchError error) {
                LogUtil.e(TAG, " error :" + error.toString());
            }

            @Override
            public void onCompletion(BasicResponseModel model) {
                List<List<String>> result =(List<List<String>>)model.body;
                LogUtil.e(TAG, "CarRentGetCitys = " + result.toString());
            }
        });




    }
}
