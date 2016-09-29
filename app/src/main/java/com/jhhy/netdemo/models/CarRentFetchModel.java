package com.jhhy.netdemo.models;

import com.jhhy.netdemo.utils.Consts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by zhangguang on 16/9/29.
 */
public class CarRentFetchModel extends BasicFetchModel {

    public JSONObject toBizJsonObject(){
        JSONObject jsonObject = super.toBizJsonObject();
        try {
            jsonObject.put(Consts.KEY_FIELD,new JSONArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonObject;
    }
}
