package com.jhhy.netdemo.biz;

import android.content.Context;
import android.os.Handler;

/**
 * Created by birney1 on 16/9/28.
 */
public class BasicActionBiz {

    private Context context;
    private Handler handler;

    public BasicActionBiz(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }
}
