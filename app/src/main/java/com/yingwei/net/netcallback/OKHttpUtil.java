package com.yingwei.net.netcallback;

import android.nfc.Tag;

import com.jhhy.cuiweitourism.net.models.FetchModel.BasicFetchModel;
import com.jhhy.cuiweitourism.net.utils.Consts;
import com.jhhy.cuiweitourism.net.utils.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jiahe008 on 2017/2/6.
 */
public class OKHttpUtil {

    private static final String TAG = "OKHttpUtil";

    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient();

    public static void post(BasicFetchModel json, Callback callback) {

        LogUtil.e(TAG, "发送数据：" + json.toBizJsonString());

        RequestBody body = RequestBody.create(JSON, json.toBizJsonString());
        Request request = new Request.Builder()
                .url(Consts.SERVER_URL)
//                .addHeader("Content-Type", "application/json;charset=utf-8")
                .post(body)
                .build();

        client.newCall(request).enqueue(callback);
    }
}
