package com.jhhy.cuiweitourism.net.biz;

import android.content.Context;
import android.os.Handler;

import com.jhhy.cuiweitourism.net.models.FetchModel.VisaHotCountry;
import com.jhhy.cuiweitourism.net.models.ResponseModel.BasicResponseModel;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchError;
import com.jhhy.cuiweitourism.net.models.ResponseModel.FetchResponseModel;
import com.jhhy.cuiweitourism.net.netcallback.BizCallback;
import com.jhhy.cuiweitourism.net.netcallback.FetchCallBack;
import com.jhhy.cuiweitourism.net.netcallback.FetchResponse;
import com.jhhy.cuiweitourism.net.netcallback.HttpUtils;


/**
 * Created by zhangguang on 16/10/9.
 */
public class VisaActionBiz extends  BasicActionBiz {
    public VisaActionBiz(Context context, Handler handler) {
        super(context, handler);
    }

    public VisaActionBiz(){
        super();

    }
    /**
     *  热门签证国家
     */

    public  void VisaGetHotCountry(BizCallback callBack){
        VisaHotCountry hotCountry = new VisaHotCountry();
        hotCountry.setBizCode("Visa_index");
        final FetchResponse fetchResponse = new FetchResponse(callBack) {
            @Override
            public void onCompletion(FetchResponseModel response) {
                BasicResponseModel returnModel = new BasicResponseModel();
                this.bizCallback.onCompletion(returnModel);

            }

            @Override
            public void onError(FetchError error) {
                this.bizCallback.onError(error);
            }
        };
        HttpUtils.executeXutils(hotCountry, new FetchCallBack(fetchResponse));
    }
}
