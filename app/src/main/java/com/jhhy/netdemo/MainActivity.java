package com.jhhy.netdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jhhy.netdemo.biz.OrderActionBiz;
import com.jhhy.netdemo.models.Order;
import com.jhhy.netdemo.utils.Consts;
import com.jhhy.netdemo.utils.LogUtil;

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

        OrderActionBiz biz = new OrderActionBiz(getApplicationContext(), handler);
        biz.getOrderDetail("01585988836818");
    }
}
