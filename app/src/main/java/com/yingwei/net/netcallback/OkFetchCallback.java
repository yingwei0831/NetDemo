package com.yingwei.net.netcallback;

import com.google.gson.Gson;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchError;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchResponseModel;
import com.jhhy.cuiweitourism.net.netcallback.FetchGenericResponse;
import com.jhhy.cuiweitourism.net.netcallback.FetchResponse;
import com.jhhy.cuiweitourism.net.utils.Consts;
import com.jhhy.cuiweitourism.net.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.ex.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiahe008 on 2017/2/7.
 */
public class OkFetchCallback<T> implements Callback {

    private static final String TAG = "OkFetchCallback";

    protected FetchGenericResponse<T> response;

    public OkFetchCallback(FetchGenericResponse<T> response) {
        this.response = response;
    }

    @Override
    public void onFailure(Call call, IOException ex) {
        String responseMsg =  ex.getMessage();
        String errorResult = ex.getLocalizedMessage();
        FetchError error = new FetchError();
        error.errroCode = -1;
        error.msg = responseMsg;
        error.info = errorResult;
        error.exceptionName = ex.toString();

        // LogUtil.e(TAG, " error.msg = " + error.msg +", error.info = "+ error.info + ", error.exceptionName = "+error.exceptionName);
        if (ex instanceof HttpException) {
            LogUtil.e(TAG, " excetpion = " + ex.toString());
        }
        else if(ex instanceof UnknownHostException){
            LogUtil.e(TAG, " excetpion = " + ex.toString());
            //Toast.makeText(getApplicationContext(), "请检查网络连接并重试", Toast.LENGTH_SHORT).show();
            error.localReason = "请检查网络连接并重试";
        }
        else if(ex instanceof ConnectException){
            LogUtil.e(TAG, " excetpion = " + ex.toString());
            error.localReason = "请检查网络连接并重试";
        }

        this.response.onError(error);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()){
            try {
                FetchResponseModel model =  new FetchResponseModel(); //new Gson().fromJson(result,FetchResponseModel.class);
                String result = response.body().string(); //返回数据
                JSONObject resultObj = new JSONObject(result);
                LogUtil.e(TAG," " + result);
                String headStr = resultObj.getString(Consts.KEY_HEAD);
                model.head = new Gson().fromJson(headStr, FetchResponseModel.HeadModel.class);
                if ("0000".equals(model.head.res_code)) { //成功
                    model.body = resultObj.getString(Consts.KEY_BODY);
                    this.response.onCompletion(model);
                }else{ //失败
                    FetchError error = new FetchError();
                    error.localReason = model.head.res_arg;
                    this.response.onError(error);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            FetchError error = new FetchError();
            error.errroCode = response.code(); //-1
            error.msg = response.message();
            error.localReason = "服务器返回失败";

            error.info = "IOException: Unexpected code + " + response;
            error.exceptionName = response.toString();

            this.response.onError(error);
//            throw new IOException("Unexpected code " + response);
//            IOException e = new IOException("Unexpected code " + response);
//            onFailure(call, e);
        }
    }
}
