package com.jhhy.netdemo.http;

import com.jhhy.netdemo.models.ResponseModel.BasicResponseModel;
import com.jhhy.netdemo.models.ResponseModel.FetchError;

/**
 * Created by birney on 2016-10-01.
 */
public abstract class BizCallback {

    public String uuid;
    public abstract void onError(FetchError error);
    public abstract void onCompletion(BasicResponseModel model);
}
