package com.jhhy.netdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jhhy.netdemo.biz.CarRentActionBiz;
import com.jhhy.netdemo.biz.OrderActionBiz;
import com.jhhy.netdemo.http.BizCallback;
import com.jhhy.netdemo.models.ResponseModel.BasicResponseModel;
import com.jhhy.netdemo.models.FetchModel.CarRentNextModel;
import com.jhhy.netdemo.models.ResponseModel.CarNextResponse;
import com.jhhy.netdemo.models.ResponseModel.CarRentDetail;
import com.jhhy.netdemo.models.ResponseModel.FetchError;
import com.jhhy.netdemo.models.TestStudent;
import com.jhhy.netdemo.models.Order;
import com.jhhy.netdemo.utils.Consts;
import com.jhhy.netdemo.utils.LogUtil;

import java.util.ArrayList;

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
                LogUtil.e(TAG, "array = " + array.toString());
            }

            @Override
            public void onError(FetchError error) {

            }
        });


        CarRentNextModel model = new CarRentNextModel("5","2","北京市昌平区史各庄","辽宁省凌源市火车站");
        carBiz.carRentNextApi(model, new BizCallback(){
            @Override
            public void onError(FetchError error) {
            }
            @Override
            public void onCompletion(BasicResponseModel model) {
                CarNextResponse next = (CarNextResponse) model.body;
                LogUtil.e(TAG, "CarNextResponse = " + next.toString());
            }
        });
    }
}
