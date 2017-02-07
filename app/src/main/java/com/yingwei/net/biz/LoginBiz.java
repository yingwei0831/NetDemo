package com.yingwei.net.biz;

import com.google.gson.Gson;
import com.jhhy.cuiweitourism.net.biz.BasicActionBiz;
import com.jhhy.cuiweitourism.net.models.FetchModel.BasicFetchModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.BasicResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchError;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.GenericResponseModel;
import com.jhhy.cuiweitourism.net.netcallback.BizCallback;
import com.jhhy.cuiweitourism.net.netcallback.BizGenericCallback;
import com.jhhy.cuiweitourism.net.netcallback.FetchGenericResponse;
import com.jhhy.cuiweitourism.net.netcallback.FetchResponse;
import com.yingwei.net.model.RequestModel.LoginRequest;
import com.yingwei.net.model.ResponseModel.LoginResponse;
import com.yingwei.net.netcallback.OKHttpUtil;
import com.yingwei.net.netcallback.OkFetchCallback;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiahe008 on 2017/2/6.
 */
public class LoginBiz extends BasicActionBiz {

//    {"head":{"code":"User_login"},"field":{"mobile":"15210656911","password":"admin123"}}

    public void login(LoginRequest fetch, BizGenericCallback<LoginResponse> callback) {

        fetch.code = "User_login";

        FetchGenericResponse<LoginResponse> fetchResponse = new FetchGenericResponse<LoginResponse>(callback) {
            @Override
            public void onCompletion(FetchResponseModel response) {
                GenericResponseModel<LoginResponse> returnModel = new GenericResponseModel<>(
                        response.head, parseJsonToObject(response, LoginResponse.class));
                this.bizCallback.onCompletion(returnModel);
            }

            @Override
            public void onError(FetchError error) {
                this.bizCallback.onError(error);
            }
        };

        OKHttpUtil.post(fetch, new OkFetchCallback<>(fetchResponse));

    }

}
