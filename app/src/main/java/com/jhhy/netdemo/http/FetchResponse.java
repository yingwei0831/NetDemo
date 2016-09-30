package com.jhhy.netdemo.http;

import com.jhhy.netdemo.models.FetchError;
import com.jhhy.netdemo.models.FetchResponseModel;

/**
 * Created by zhangguang on 16/9/30.
 */
public interface FetchResponse {

    public void onCompletion(FetchResponseModel response);

    public void onError(FetchError error);
}
