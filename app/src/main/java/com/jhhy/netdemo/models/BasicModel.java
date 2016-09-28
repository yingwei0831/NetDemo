package com.jhhy.netdemo.models;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by birney1 on 16/9/28.
 */
public class BasicModel {

    public BasicModel(){

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
        return map;
    }
}
