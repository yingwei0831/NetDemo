package com.yingwei.net.biz;

import com.google.gson.Gson;
import com.jhhy.cuiweitourism.net.models.FetchModel.BasicFetchModel;
import com.yingwei.net.model.RequestModel.LoginRequest;
import com.yingwei.net.model.ResponseModel.LoginResponse;
import com.yingwei.net.netcallback.OKHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiahe008 on 2017/2/6.
 */
public class LoginBiz {

//    {"head":{"code":"User_login"},"field":{"mobile":"15210656911","password":"admin123"}}

    private void login(BasicFetchModel fetch, LoginResponse response) {

        fetch.code = "User_login";

        OKHttpUtil.post(fetch, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    LoginResponse loginResponse = new Gson().fromJson(response.body().charStream(), LoginResponse.class);

                }else{
//                    throw new IOException("Unexpected code " + response);
                    IOException e = new IOException(response.message());
                    onFailure(call, e);
                }
            }
        });
    }

}
