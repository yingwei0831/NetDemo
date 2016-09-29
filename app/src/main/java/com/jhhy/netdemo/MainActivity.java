package com.jhhy.netdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;

import com.google.gson.Gson;
import com.jhhy.netdemo.biz.CarRentActionBiz;
import com.jhhy.netdemo.biz.OrderActionBiz;
import com.jhhy.netdemo.models.TestStudent;
import com.jhhy.netdemo.models.Order;
import com.jhhy.netdemo.utils.Consts;
import com.jhhy.netdemo.utils.LogUtil;

import java.io.StringReader;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Consts.MESSAGE_ORDER_DETAIL:
                    if (msg.arg1 == 1){
                        Order order = (Order) msg.obj;
                        LogUtil.e("info", "info = "+order.toString());
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

        TestStudent student = new TestStudent("haha","beijingshi changyangqu",30);

        LogUtil.e("Test", "student = "+student.toJsonString());

        String objectStr="{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}";
//
        String arrayStr="[{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}]";
        //JSONObject jsonObject = new JSONObject(objectStr); /
//
//
//        JsonReader reader = new JsonReader(new StringReader(objectStr));
//
//         // TestSudent student2 = new Gson().fromJson(objectStr,TestSudent.class);
//
//        TestStudent tt = new Gson().fromJson(objectStr,TestStudent.class);
//
//
//        try {
//            Map<String,Object> map = tt.toMapObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        student.setBizCode("xxxxxxx");
//        LogUtil.e("Test","studentBizJson = " + student.toBizJsonString());


        CarRentActionBiz carBiz = new CarRentActionBiz(getApplicationContext(),handler);
        carBiz.fetchCarRentalServiceDetail();


    }
}
