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

    /**
     * Called when the request could not be executed due to cancellation, a connectivity problem or
     * timeout. Because networks can fail during an exchange, it is possible that the remote server
     * accepted the request before the failure.
     *
     * @param call
     * @param ex
     */
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

    /**
     * Called when the HTTP response was successfully returned by the remote server. The callback may
     * proceed to read the response body with {@link Response#body}. The response is still live until
     * its response body is {@linkplain ResponseBody closed}. The recipient of the callback may
     * consume the response body on another thread.
     * <p>
     * <p>Note that transport-layer success (receiving a HTTP response code, headers and body) does
     * not necessarily indicate application-layer success: {@code response} may still indicate an
     * unhappy HTTP response code like 404 or 500.
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call call, Response response)
//            throws IOException
    {
        if (response.isSuccessful()){
            try {
                FetchResponseModel model =  new FetchResponseModel(); //new Gson().fromJson(result,FetchResponseModel.class);
                String result = response.body().string(); //返回数据
                JSONObject resultObj = new JSONObject(result);
                LogUtil.e(TAG," " + result);
                String headStr = resultObj.getString(Consts.KEY_HEAD);
                model.head = new Gson().fromJson(headStr, FetchResponseModel.HeadModel.class);
                model.body = resultObj.getString(Consts.KEY_BODY);
                if ("0000".equals(model.head.res_code)) { //成功
                    this.response.onCompletion(model);
                }else{ //失败
                    FetchError error = new FetchError();
                    error.localReason = model.head.res_arg;
                    this.response.onError(error);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
