package com.jhhy.netdemo.models;

import com.google.gson.Gson;
import com.jhhy.netdemo.utils.Consts;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by birney1 on 16/9/28.
 */
 public abstract class BasicFetchModel  {

    protected String code;


    public BasicFetchModel(){
    }

    public void setBizCode(String code){
        this.code = code;
    }


    public String toJsonString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public Map<String, Object> toMapObject() throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(this));
        }
        map.remove("$change");
        map.remove("code");
        return map;
    }

    public String toBizJsonString(){

        JSONObject json = this.toBizJsonObject();
        String entityString = json.toString();
        return entityString;
    }

    public JSONObject toBizJsonObject(){
        JSONObject json = new JSONObject();

        try {
            JSONObject headJson = new JSONObject();
            headJson.put(Consts.KEY_CODE,this.code);
            json.put(Consts.KEY_HEAD, headJson);
            json.put(Consts.KEY_FIELD, toFieldJsonObject(this.toMapObject()));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    protected  JSONObject toFieldJsonObject(Map<String, Object> stringObjectMap) {
        JSONObject fieldObj = new JSONObject();
        if(null == stringObjectMap) return fieldObj;
        try {
            for(String key : stringObjectMap.keySet()){
                fieldObj.put(key, stringObjectMap.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fieldObj;
    }

}
